package me.salat23.piss

import api.longpoll.bots.LongPollBot
import api.longpoll.bots.model.events.messages.MessageNew
import java.io.File
import kotlin.random.Random

class Bot() : LongPollBot() {

    private val env = System.getenv()
    private val dvachTexts: List<String>

    init {
        val path = this.javaClass.getResourceAsStream("/2ch/wpis.txt")
        dvachTexts = path?.bufferedReader()?.readText()?.split("%~%~%") ?: listOf()
        println(dvachTexts.size)
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
    override fun getAccessToken(): String = env["PISS_TOKEN"] ?: ""
    override fun onMessageNew(messageNew: MessageNew?) {
        try {
            if (messageNew?.message?.hasText() == true) {
                var text = messageNew.message.text!!
                
                if (text.startsWith("!терпила?") && (messageNew.message.fromId == 255136148 || messageNew.message.fromId == 664582531)) {
                    vk.messages.send()
                        .setPeerId(messageNew.message.peerId)
                        .setReplyTo(messageNew.message.id)
                        .setMessage("Ну да, тот еще терпильник...")
                        .execute()
                    return
                }
                
                if (text.startsWith("!слит?") && (messageNew.message.fromId == 255136148 || messageNew.message.fromId == 664582531)) {
                    vk.messages.send()
                        .setPeerId(messageNew.message.peerId)
                        .setReplyTo(messageNew.message.id)
                        .setMessage("УУУУ СЛИТ!!!")
                        .execute()
                    return
                }

                if (text.startsWith("!я прав?") && (messageNew.message.fromId == 255136148 || messageNew.message.fromId == 664582531)) {
                    vk.messages.send()
                        .setPeerId(messageNew.message.peerId)
                        .setReplyTo(messageNew.message.id)
                        .setMessage("Да, ты абсолютно прав!!!")
                        .execute()
                    return
                }
                
                if (text.startsWith("!переобулся?")) {
                    vk.messages.send()
                        .setPeerId(messageNew.message.peerId)
                        .setReplyTo(messageNew.message.id)
                        .setMessage("100% переобувочная случилась")
                        .execute()
                    return
                }

                if (text.startsWith("!вывез?")) {
                    vk.messages.send()
                        .setPeerId(messageNew.message.peerId)
                        .setReplyTo(messageNew.message.id)
                        .setMessage("Нет, чел не вывез очевидно")
                        .execute()
                    return
                }

                text = text.replace("[^A-Za-zА-Яа-я]", "")

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
