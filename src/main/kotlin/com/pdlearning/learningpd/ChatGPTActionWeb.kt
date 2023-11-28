package com.pdlearning.learningpd

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.awt.Desktop
import java.net.URI
import com.intellij.openapi.util.TextRange


class ChatGPTActionWeb : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val url = "https://chat.openai.com/"

        val editor = e.getData(CommonDataKeys.EDITOR)
        val document = editor?.document
        val selection = editor?.selectionModel

        if (selection?.hasSelection() == true) {
            val start = selection.selectionStart
            val end = selection.selectionEnd

            val selectedCode = document?.getText(TextRange(start, end))

            if (selectedCode != null) {
                copyToClipboard(selectedCode)
            }
        }

        try {
            Desktop.getDesktop().browse(URI(url))
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }

    private fun copyToClipboard(text: String) {
        val stringSelection = StringSelection(text + "\nНайди ошибку в коде и исправь её")
        val clipboard = Toolkit.getDefaultToolkit().systemClipboard
        clipboard.setContents(stringSelection, null)
    }
}
