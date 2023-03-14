package me.salat23.piss

import api.longpoll.bots.LongPollBot
import api.longpoll.bots.model.events.messages.MessageNew
import java.io.File
import kotlin.random.Random

class Bot() : LongPollBot() {

    private val envToken = System.getenv("PISS_TOKEN")
    private val dvachTexts: List<String>

    init {
        val path = this.javaClass.getResource("/2ch/")?.file
        if (path != null) {
            val texts = mutableListOf<String>()
            val file = File(path)
            file.walk().forEach { file ->
                texts.addAll(file.readText().split("%~%~%"))
            }
            dvachTexts = texts.toList()
        } else {
            dvachTexts = listOf()
        }
    }
    private val keywords = listOf(
        "python",
        "pyhton",
        "пайтон",
        "питон",
        "питхон",
        "пэйтон",
        "питончик",
        "питухон"
    )
    override fun getAccessToken(): String = envToken
    override fun onMessageNew(messageNew: MessageNew?) {
        try {
            if (messageNew?.message?.hasText() == true) {
                val text = messageNew.message.text!!.replace("[^A-Za-zА-Яа-я]", "")

                if (text.startsWith("!2ch") && dvachTexts.isNotEmpty()) {
                    vk.messages.send()
                        .setPeerId(messageNew.message.peerId)
                        .setReplyTo(messageNew.message.id)
                        .setMessage(dvachTexts[Random.Default.nextInt(0, dvachTexts.size-1)])
                        .execute()
                    return
                }

                if (messageNew.message.fromId == 255136148 && !text.startsWith("!")) return
                keywords.forEach { word ->
                    if (text.lowercase().contains(word.lowercase())) {
                        vk.messages.send()
                            .setPeerId(messageNew.message.peerId)
                            .setReplyTo(messageNew.message.id)
                            .setMessage("Питон говно")
                            .execute()
                    }
                }
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }
}
