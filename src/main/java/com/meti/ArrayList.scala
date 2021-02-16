package com.meti

object ArrayList {
  def empty[T] = new ArrayList[T](new Array[Any](10), 0)
}

class ArrayList[T](val elements: Array[Any], val elementsSize: Int) extends List[T] {
  @throws[CollectionException]
  override def add(element: T): List[T] = set(elementsSize, element)

  @throws[CollectionException]
  override def set(index: Int, element: T): List[T] = {
    if (index < 0) throw new IndexException("Index is negative.")
    val newElements = if (index < elements.length) elements else resize(index)
    val newSize = if (index + 1 > elementsSize) index + 1 else elementsSize
    newElements(index) = element
    new ArrayList[T](newElements, newSize)
  }

  private def resize(index: Int): Array[Any] = {
    val copy = new Array[Any](calculateCapacity(index))
    for (i <- 0 until elementsSize) {
      copy(i) = elements(i)
    }
    copy
  }

  private def calculateCapacity(index: Int) = {
    var toUse = if (elements.length == 0) 1
    else elements.length
    while (index >= toUse) toUse *= 2
    toUse
  }

  override def stream: Stream[T] = new AbstractStream[T]() {
    private var counter = 0

    @throws[StreamException]
    override def head: T = if (counter < elementsSize) {
      counter += 1
      elements(counter - 1).asInstanceOf[T]
    }
    else throw EndOfStreamException("No more elements in list left.")
  }
}