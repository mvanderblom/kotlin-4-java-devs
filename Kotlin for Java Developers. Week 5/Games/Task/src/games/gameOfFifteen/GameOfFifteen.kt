package games.gameOfFifteen

import board.Direction
import board.GameBoard

import board.createSquareBoard
import board.Cell
import board.createGameBoard
import games.game.Game

/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */
fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game =
        GameOfFifteen(initializer)

class GameOfFifteen(private val initializer: GameOfFifteenInitializer) : Game {

    private val board = createGameBoard<Int?>(4)
    private var emptyCell = Cell(4, 4)

    override fun initialize() {
        initializer.initialPermutation
                .zip(board.getAllCells())
                .forEach { (v, c) -> board[c] = v}
    }

    override fun canMove() = true

    override fun hasWon(): Boolean {
        return (1..15)
            .zip(board.getAllCells().map { c -> board[c] })
            .all { (x, y) -> x == y }
    }

    override fun processMove(direction: Direction) {
        val oneDirection = when(direction){
            Direction.UP -> Direction.DOWN
            Direction.DOWN -> Direction.UP
            Direction.LEFT -> Direction.RIGHT
            Direction.RIGHT -> Direction.LEFT
        }

        with(board) {
            val neighbour = emptyCell.getNeighbour(oneDirection)
            neighbour?.let {
                val neighbourVal = get(it)
                set(emptyCell, neighbourVal)
                set(it, null)
                emptyCell = it
            }
        }
    }

    override fun get(i: Int, j: Int): Int? = board.run { get(getCell(i, j)) }

}
