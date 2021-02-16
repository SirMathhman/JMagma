package com.meti

abstract class AbstractStream[T] extends Stream[T] {
	override def fold(folder: F2[T, T, T]): Option[T] = headOptionally.map(fold(_, folder))

	override def fold[R](initial: R, folder: F2[R, T, R]): R = {
		var current = initial
		var continue = true
		while (continue) {
			try {
				current = folder(current, head)
			} catch {
				case _: EndOfStreamException => continue = false
				case e: Exception => throw e
			}
		}
		current
	}

	override def headOptionally: Option[T] = {
		try Some(head) catch {
			case _: EndOfStreamException => new None[T]
		}
	}

	override def map[R](mapper: F1[T, R]): Stream[R] = new AbstractStream[R] {
		override def head: R = try {
			mapper(AbstractStream.this.head)
		} catch {
			case e: EndOfStreamException => throw e
			case e: Exception => throw StreamException(cause = e)
		}
	}

	override def allMatch(predicate: F1[T, Boolean]): Boolean = {
		var continue = true
		while (continue)
			try if (!predicate(head))
				return false
			catch {
				case _: EndOfStreamException => continue = false
			}
		true
	}

	override def mapE1[R](mapper: F1E1[T, R, _]): AbstractStream[R] = new AbstractStream[R]() {
		@throws[StreamException]
		override def head: R = try mapper.apply(AbstractStream.this.head)
		catch {
			case e: EndOfStreamException => throw e
			case e: Exception => throw StreamException(cause = e)
		}
	}
}