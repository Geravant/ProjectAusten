package pfb.austen

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Reads a book from a text file and produces word usage information.
 */

fun main(args: Array<String>) {
    val book = Book(Paths.get("src/main/resources/books/PrideAndPrejudice.txt"))
    val totalWords = book.histogram.totalWords()
    println("Total word count: $totalWords")
    val file = Paths.get("PandPWords.csv")
    book.histogram.toCSV(file)
}

class Book(bookFile: Path) {
    val histogram = Histogram()

    init {
        val lines = Files.readAllLines(bookFile)
        for (str in lines) {
            val line = Line(str)
            for (word in line.words){
                histogram.record(word)
            }
        }
    }
}
