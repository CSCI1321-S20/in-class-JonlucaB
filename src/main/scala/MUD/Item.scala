package MUD

case class Item(name: String, desc: String) {
    def toString(): String = name+": "+desc+"\n"
}