package board

fun createSquareBoard(width: Int): SquareBoard = SquareBoardImpl(width)
fun <T> createGameBoard(width: Int): GameBoard<T> = GameBoardImpl<T>(width)


open class SquareBoardImpl(override val width: Int) : SquareBoard {

    val allCells = (1..width).flatMap { x ->
        (1..width).map { y -> Cell(x, y) }
    }

    override fun getAllCells(): Collection<Cell> = this.allCells;
    override fun getCellOrNull(i: Int, j: Int): Cell? = allCells.find { c -> c.i == i && c.j == j }
    override fun getCell(i: Int, j: Int): Cell = getCellOrNull(i, j) ?: throw IllegalArgumentException()

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        val cells = getAllCells().filter { c -> c.i == i && c.j in jRange }
        if (jRange.first > jRange.last)
            return cells.reversed()
        return cells
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        val cells = getAllCells().filter { c -> c.j == j && c.i in iRange }
        if (iRange.first > iRange.last)
            return cells.reversed()
        return cells
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        return when (direction) {
            Direction.UP -> getCellOrNull(this.i - 1, this.j)
            Direction.DOWN -> getCellOrNull(this.i + 1, this.j)
            Direction.LEFT -> getCellOrNull(this.i, this.j - 1)
            Direction.RIGHT -> getCellOrNull(this.i, this.j + 1)
        }
    }

}

data class GameBoardImpl<T>(val _width: Int) : SquareBoardImpl(_width), GameBoard<T> {

    val cellData = mutableMapOf<Cell, T?>();

    init {
        getAllCells().forEach { c -> cellData.put(c, null) }
    }

    override fun get(cell: Cell): T? {
        return cellData.get(cell)
    }

    override fun set(cell: Cell, value: T?) {
        cellData.put(cell, value)
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {
        return cellData.entries
                .filter { (_, v) -> predicate.invoke(v) }
                .map { (c, _) -> c }
    }

    override fun find(predicate: (T?) -> Boolean): Cell? {
        return cellData.entries
                .find { (_, v) -> predicate.invoke(v) }
                ?.key
    }

    override fun any(predicate: (T?) -> Boolean): Boolean {
        return cellData.entries
                .any { (_, v) -> predicate.invoke(v) }
    }

    override fun all(predicate: (T?) -> Boolean): Boolean {
        return cellData.entries
                .all { (_, v) -> predicate.invoke(v) }
    }

}