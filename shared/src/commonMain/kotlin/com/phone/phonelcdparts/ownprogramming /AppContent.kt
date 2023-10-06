import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun AppContent() {
    var expression by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        // Input TextField
        TextField(
            value = expression,
            onValueChange = { expression = it },
            modifier = Modifier.fillMaxWidth().height(150.dp),
            singleLine = false,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    result = evaluateExpression(expression)
                }
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Run Button
        Button(
            onClick = {
                result = evaluateExpression(expression)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Run")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Output Text
        Text(
            text = "Result: $result",
            style = MaterialTheme.typography.h6,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

fun evaluateExpression(input: String): String {
    val interpreter = Interpreter(input)
    return interpreter.expr()
}




// SimpleInterpreter.kt

// Token types
enum class TokenType {
    INTEGER,
    PLUS,
    MINUS,
    MULTIPLY,
    DIVIDE,
    END,
    JO,     // "jo" (if)
    TO,     // "to" (else)
    CHPAP,   // "chhap" (print)
    STRING,
    LEFT_PAREN,  // "("
    RIGHT_PAREN,
    EQUALS,      // "=="
    EQUALS_VALUE
}

// Token structure
data class Token(val type: TokenType, val value: Int = 0, val stringValue: String = "")

// Lexer class
class Lexer(private val input: String) {
    private var position = 0

    private fun isCurrentCharWhitespace(): Boolean {
        return input[position].isWhitespace()
    }

    private fun skipWhitespace() {
        while (position < input.length && isCurrentCharWhitespace()) {
            position++
        }
    }

    private fun isKeyword(keyword: String): Boolean {
        val endPosition = position + keyword.length
        if (endPosition <= input.length) {
            val substring = input.substring(position, endPosition)
            return substring == keyword
        }
        return false
    }
    private fun isParenthesis(char: Char): Boolean {
        return char == '(' || char == ')'
    }

    fun getNextToken(): Token {
        skipWhitespace()

        if (position >= input.length) {
            return Token(TokenType.END)
        }

        val currentChar = input[position]

        if (currentChar.isDigit()) {
            // Handle integer token
            val buffer = StringBuilder()
            while (position < input.length && input[position].isDigit()) {
                buffer.append(input[position])
                position++
            }
            return Token(TokenType.INTEGER, buffer.toString().toInt())
        } else if (isParenthesis(currentChar)) {
            // Handle parentheses
            position++
            return when (currentChar) {
                '(' -> Token(TokenType.LEFT_PAREN)
                ')' -> Token(TokenType.RIGHT_PAREN)
                else -> throw IllegalArgumentException("Invalid character: $currentChar")
            }
        }else if (isKeyword("jo")) {
            position += 2
            return Token(TokenType.JO)
        } else if (isKeyword("to")) {
            position += 2
            return Token(TokenType.TO)
        } else if (isKeyword("chhap")) {
            position += 5
            return Token(TokenType.CHPAP)
        }else if (currentChar == '=' && position + 1 < input.length && input[position + 1] == '=') {
            // Handle comparison operator "=="
            position += 2
            return Token(TokenType.EQUALS)
        }

        // Handle operator tokens
        position++
        return when (currentChar) {
            '+' -> Token(TokenType.PLUS)
            '-' -> Token(TokenType.MINUS)
            '*' -> Token(TokenType.MULTIPLY)
            '/' -> Token(TokenType.DIVIDE)
            else -> throw IllegalArgumentException("Invalid character: $currentChar")
        }
    }
}

// Interpreter class
class Interpreter(input: String) {
    private val lexer = Lexer(input)

    fun expr(): String {
        var token = lexer.getNextToken()
        val result = StringBuilder()

        while (token.type != TokenType.END) {
            when (token.type) {
                TokenType.JO -> {
                    token = lexer.getNextToken()
                    if (token.type == TokenType.INTEGER && token.value != 0) {
                        token = lexer.getNextToken()
                        if (token.type == TokenType.JO) {
                            // Nested "jo" statement
                            val nestedResult = expr()
                            if (token.type == TokenType.TO) {
                                // Skip the "to" block and move to the next token
                                while (token.type != TokenType.CHPAP && token.type != TokenType.END) {
                                    token = lexer.getNextToken()
                                }
                            } else {
                                throw IllegalArgumentException("Expected 'to' after nested 'jo'")
                            }
                            if (nestedResult.isNotEmpty()) {
                                token = lexer.getNextToken()
                            }
                        }
                    } else {
                        // Skip the "jo" block and move to the next token
                        while (token.type != TokenType.TO && token.type != TokenType.CHPAP && token.type != TokenType.END) {
                            token = lexer.getNextToken()
                        }
                        if (token.type == TokenType.TO) {
                            token = lexer.getNextToken()
                        }
                    }
                }

                TokenType.TO -> {
                    // Skip the "to" block and move to the next token
                    while (token.type != TokenType.CHPAP && token.type != TokenType.END) {
                        token = lexer.getNextToken()
                    }
                }

                TokenType.CHPAP -> {
                    token = lexer.getNextToken()
                    if (token.type == TokenType.STRING) {
                        // Append the string value to the result
                        result.append(token.stringValue)
                        token = lexer.getNextToken()
                    }
                }

                TokenType.INTEGER -> {
                    // Append the integer value to the result
                    result.append(token.value)
                    token = lexer.getNextToken()
                }

                TokenType.PLUS, TokenType.MINUS, TokenType.MULTIPLY, TokenType.DIVIDE -> {
                    // Append the operator to the result
                    result.append(token.type)
                    token = lexer.getNextToken()
                }
                TokenType.LEFT_PAREN -> {
                    // Handle parentheses
                    token = lexer.getNextToken()
                    result.append('(')
                }
                TokenType.RIGHT_PAREN -> {
                    token = lexer.getNextToken()
                    result.append(')')
                }
                TokenType.EQUALS -> {
                    token = lexer.getNextToken()
                    result.append("==")
                }

                else -> {
                    throw IllegalArgumentException("Unexpected token: ${token.type}")
                }
            }
        }

        return result.toString()
    }
}

