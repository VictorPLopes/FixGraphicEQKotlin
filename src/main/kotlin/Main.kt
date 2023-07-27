import java.awt.FileDialog
import java.io.File
import kotlin.system.exitProcess

fun main() {
    // App header
    println("""
            FixGraphicEQ (Kotlin Version) v1.1.0
                Made by VictorPL
                          
            This program will offset values in a GraphicEQ file generated by https:\\autoeq.app by a given amount.
            
            Press ENTER to select an input file...
    """.trimIndent())
    
    pause()
    
    // Get input file
    val inputFileDialog = FileDialog(null as java.awt.Frame?, "Select input file", FileDialog.LOAD)
    inputFileDialog.isVisible = true
    val inputFileName: String? = inputFileDialog.file
    if (inputFileName == null) {
        println("\nOperation cancelled. Press any key to exit...")
        pause()
        exitProcess(0)
    }
    val inputFileFolder: String = inputFileDialog.directory
    
    // Gets the offset value from the user
    var offsetValue: Float?
    do {
        println("\nPlease input a valid gain offset value to be applied to the file (e.g. 12.2): ")
        offsetValue = readln().toFloatOrNull()
    } while (offsetValue == null)
    
    // List of frequencies and gains
    val frequenciesGains = mutableListOf<FrequencyGain>()

    // Beginning of every GraphicEQ file
    val header = "GraphicEQ: "

    // Reads the file and adds the values to the list
    try {
        for (frequencyGain in File("$inputFileFolder\\$inputFileName").readText().removePrefix(header).split("; ")) {
            val values = FrequencyGain(frequencyGain.split(" ")[0].toInt(), frequencyGain.split(" ")[1].toFloat())
            frequenciesGains.add(values)
        }
    }
    catch (e: Exception) {
        println("\nError reading file: ${e.message}")
        throw e
    }

    // Creates a new string with the new values
    var newValues = header
    val df = java.text.DecimalFormat("#.#")
    for (frequencyGain in frequenciesGains) {
        newValues += "${frequencyGain.frequency} ${df.format(frequencyGain.gain + offsetValue)}; "
    }
    
    // Writes the new file
    try {
        val outputFilePath = "$inputFileFolder\\${inputFileName.removeSuffix(".txt")}_offset$offsetValue.txt"
        File(outputFilePath).writeText(newValues.removeSuffix("; "))
        println("\nFile written successfully: $outputFilePath")
    }
    catch (e: Exception) {
        println("\nError writing file: ${e.message}")
        throw e
    }
    finally {
        println("\nPress any key to exit...")
        pause()
    }
    exitProcess(0)
}

// Pauses the program until a key is pressed, probably a stupid way to do it without sending a system pause command
fun pause() {
    readlnOrNull()
}
