import java.io.File

/**
 * エントリポイント
 */
fun main(args: Array<String>) {

    // 対象のディレクトリ名を設定
    val path = """D:\work\md"""

    // 修正対象のファイルを取得
    val targetFileList = File(path)
        .walkTopDown()
        .filter { it.isFile && it.extension == "md" }

    for (file in targetFileList) {
        replaceWhiteSpace(file)
    }
}

/**
 * 末尾の半角スペース2つを\に変換します。
 */
private fun replaceWhiteSpace(file: File) {

    // 末尾に半角スペース2つ
    val whiteSpace = Regex(""" {2}$""")

    val writeLines = mutableListOf<String>()

    file.forEachLine {

        // 正規表現で部分一致する場合
        if (whiteSpace.containsMatchIn(it)) {

            val text = it.substring(0, it.length - 2) + """\"""
            writeLines.add(text)

        } else {
            writeLines.add(it)
        }
    }

    file.writeText(
        writeLines.joinToString(System.getProperty("line.separator"))
                + System.getProperty("line.separator")
    )
}
