package com.phone.phonelcdparts.android

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.pow

data class Item(val color: Color, val char: Char)

@Composable
fun GamePlay() {
    val maxCombinations = 2.0.pow(12).toInt()
    var str = ""

    for (i in 0 until maxCombinations) {
        val binaryString = Integer.toBinaryString(i)
        val paddedBinaryString = binaryString.padStart(12, '0')
        str += paddedBinaryString
    }
    val characterList = str.toList()

    val items = remember { mutableListOf<Item>() }

    var state = "match"
    var count = 0
    val minRedSequenceLength = 8
    var redSequenceCount = 0
    var totalAmount by remember { mutableStateOf(1000) } // Default total amount
    var investAmount by remember { mutableStateOf(100) } // Default invest amount

    for ((index, character) in characterList.withIndex()) {
        if (count == 2) {
            if (state == "match") {
                state = "not_match"
            } else if (state == "not_match") {
                state = "match"
            } else {
                state = "match"
            }
            count = 0
        } else {
            count++
        }

        var color = if (index == 0) {
            Color.Green
        } else if (state == "match") {
            colorForMatch(index, characterList)
        } else {
            colorForNotMatch(index, characterList)
        }

        items.add(Item(color, character))

        if (color == Color.Red) {
            totalAmount -= investAmount // Reduce total amount on red
            investAmount *= 2 // Double the investment
            redSequenceCount++
        } else {
            redSequenceCount = 0
            totalAmount += investAmount // Add investment back on green
            investAmount = 10
        }
        println("fdfwer34ier      Invest Amount: $investAmount")
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        contentPadding = PaddingValues(5.dp)
    ) {
        items(items.size) { item ->
            Text(
                modifier = Modifier
                    .size(20.dp)
                    .background(items[item].color)
                    .padding(5.dp),
                text = items[item].char.toString(),
                fontSize = 10.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }

    if (redSequenceCount >= minRedSequenceLength) {
        println("Red sequence detected!")
    }

    println("fdfwer34ier    Total Amount: $totalAmount")

}

private fun colorForMatch(index: Int, characterList: List<Char>): Color {
    return if (characterList[index] == characterList[index - 1]) {
        Color.Green
    } else {
        Color.Red
    }
}

private fun colorForNotMatch(index: Int, characterList: List<Char>): Color {
    return if (characterList[index] != characterList[index - 1]) {
        Color.Green
    } else {
        Color.Red
    }
}

@Preview
@Composable
fun StartGame() {
    GamePlay()
}