import api.longpoll.bots.LongPollBot
import api.longpoll.bots.model.events.messages.MessageNew

class Bot: LongPollBot() {

    private val keywords = listOf(
        "python",
        "pyhton",
        "пайтон",
        "питон",
        "питхон",
        "пэйтон",
        "питончик",
    )
    override fun getAccessToken(): String {
        return "vk1.a.zhXc_AJrtbl6zOSYLOowWmMTR7PTDBSWkFW4luT-Y2-QCsS6SFvD2JOoQoQxG2SFLDcGl7JgvYaYUprF8O_BG3LF0vuRFtUCttNlh-40ulb40ePWKrEji5agHRHE0TL95PWCbTBt5tzY4xFRD5urOEOpvbJ-ZRxmpsKKzEs-yAlBH8RI72huTJ9lNORAVndGqeuwtB8iW8rjUfZSq6L7wA"
    }
    override fun onMessageNew(messageNew: MessageNew?) {
        try {
            if (messageNew?.message?.hasText() == true) {
                val text = messageNew.message.text!!
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
