package com.meti

trait Stream[T] {
	@throws[StreamException]
	def allMatch(predicate: F1[T, Boolean]): Boolean

	@throws[EndOfStreamException]
	def fold[R](initial: R, folder: F2[R, T, R]): R

	@throws[EndOfStreamException]
	def fold(folder: F2[T, T, T]): Option[T]

	@throws[StreamException]
	def head: T

	@throws[StreamException]
	def headOptionally: Option[T]

	def map[R](mapper: F1[T, R]): Stream[R]

	def mapE1[R](mapper: F1E1[T, R, _]): Stream[R]
}