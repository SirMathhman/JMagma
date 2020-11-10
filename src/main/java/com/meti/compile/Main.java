package com.meti.compile;

import com.meti.api.io.Directory;
import com.meti.api.io.Extant;
import com.meti.api.io.File;
import com.meti.api.io.Path;
import com.meti.api.log.Logger;
import com.meti.api.core.Option;
import com.meti.compile.path.NIOScriptPath;
import com.meti.compile.path.ScriptPath;

import java.io.IOException;

import static com.meti.api.io.JavaOutStream.OutStream;
import static com.meti.api.io.NIOFileSystem.FileSystem_;
import static com.meti.api.log.Logger.Level.*;
import static com.meti.api.log.OutStreamLogger.Logger;
import static com.meti.api.core.None.None;
import static com.meti.api.core.Some.Some;
import static com.meti.api.stream.ArrayStream.Stream;
import static com.meti.compile.MagmaCompiler.MagmaCompiler;

public class Main {
    private static final Logger logger = Logger(OutStream(System.out));
    private static final String SourceFormat = "%s.mg";
    private static final Directory Root = FileSystem_.Root().asDirectory();
    private static final Path SourceDirectory = Root.resolve("source");
    private static final Path Build = Root.resolve(".build");
    private static final Path Target = Root.resolve("target.c");

    public static void main(String[] args) {
        int written = process();
        String format = "Wrote %d characters to target at '%s'.";
        String message = format.formatted(written, Target);
        logger.logIgnored(Info, message);
    }

    private static int process() {
        try {
            return Build.ensuringExistenceAsFile(Main::processWithBuild);
        } catch (IOException e) {
            String format0 = "Failed to create build file at '%s'.";
            String message0 = format0.formatted(Build);
            logger.logExceptionallyIgnored(Severe, message0, e);
            return 0;
        }
    }

    private static int processWithBuild(Extant build) {
        try {
            return SourceDirectory.ensuringExistenceAsDirectory(directory -> process(build, directory));
        } catch (IOException e) {
            String format0 = "Failed to create source directory at '%s'.";
            String message0 = format0.formatted(SourceDirectory);
            logger.logExceptionallyIgnored(Severe, message0, e);
            return 0;
        }
    }

    private static int process(Extant extant, Directory directory) {
        try {
            return processExceptionally(extant, directory);
        } catch (IOException e) {
            logger.logExceptionallyIgnored(Warning, "Failed to read build file.", e);
            return 0;
        }
    }

    private static int processExceptionally(Extant extant, Directory directory) throws IOException {
        String content = extant.readAsString();
        if (!content.isBlank()) return processBuildContent(content, directory);
        logger.logIgnored(Severe, "No entry point was found.");
        return 0;
    }

    private static int processBuildContent(String content, Directory directory) throws IOException {
        var mainFile = formatEntry(content, directory);
        var output = compile(mainFile, directory);
        var target = deletePreviousTarget();
        var extant = target.ensure();
        return writeToTarget(extant, output);
    }

    private static Path formatEntry(String content, Directory directory) {
        String trimmed = content.trim();
        return trimmed.contains(".") ?
                formatEntryWithPackage(trimmed, directory) :
                formatEntrySimply(trimmed, directory);
    }

    private static Path formatEntryWithPackage(String trimmed, Directory directory) {
        int separator = trimmed.lastIndexOf('.');
        String packageSlice = trimmed.substring(0, separator);
        String packageTrim = packageSlice.trim();

        String nameSlice = trimmed.substring(separator + 1);
        String name = nameSlice.trim();

        String[] packageArray = packageTrim.split("\\.");
        String formatted = SourceFormat.formatted(name);
        return Stream(packageArray)
                .foldLeft(directory, Directory::resolveDirectory)
                .resolve(formatted);
    }

    private static Path formatEntrySimply(String trimmed, Directory directory) {
        String format = "%s.mg";
        String formatted = format.formatted(trimmed);
        return directory.resolve(formatted);
    }

    private static String compile(Path mainFile, Directory directory) {
        return mainFile
                .mapExistenceAsFile(file -> compile(directory, file))
                .orElseSupply(() -> logNoEntryPoint(mainFile));
    }

    private static String logNoEntryPoint(Path mainFile) {
        String format = "Entry point at '%s' did not exist.";
        String message = format.formatted(mainFile);
        logger.logIgnored(Severe, message);
        return "";
    }

    private static String compile(Directory directory, Extant file) {
        String value = readContent(file);
        ScriptPath scriptPath = new NIOScriptPath(directory);
        Compiler compiler = MagmaCompiler(scriptPath);
        return compiler.compile(value);
    }

    private static String readContent(Extant mainExtant) {
        try {
            return mainExtant.readAsString();
        } catch (IOException e) {
            String format = "Failed to read main file at '%s'.";
            String message = format.formatted(mainExtant);
            logger.logExceptionallyIgnored(Severe, message, e);
            return "";
        }
    }

    private static File<Extant> deletePreviousTarget() {
        return Target.mapExistenceAsFile(Main::deleteTarget)
                .flatMap(extinctOption -> extinctOption)
                .peek(Main::logDeletion)
                .orElseSupply(Main::logExtinct);
    }

    private static File<Extant> logExtinct() {
        String format = "No target file existed at '%s'.";
        String message = format.formatted(Target);
        logger.logIgnored(Info, message);
        return Target.asFile();
    }

    private static void logDeletion(File<Extant> file) {
        String format = "Previous target file was deleted at '%s'.";
        String message = format.formatted(file);
        logger.logIgnored(Warning, message);
    }

    private static Option<File<Extant>> deleteTarget(Extant extant) {
        try {
            return Some(extant.delete());
        } catch (IOException e) {
            String format0 = "Failed to delete previous target file at '%s'.";
            String message0 = format0.formatted(Target);
            logger.logIgnored(Warning, message0);
            return None();
        }
    }

    private static int writeToTarget(Extant extant, CharSequence output) {
        try {
            extant.write(output);
            return output.length();
        } catch (IOException e) {
            logTargetWritingFailure(output.length());
            return 0;
        }
    }

    private static void logTargetWritingFailure(int length) {
        String format = "Failed to write output to '%s' of size %d.";
        String message = format.formatted(Target, length);
        logger.logIgnored(Severe, message);
    }
}
