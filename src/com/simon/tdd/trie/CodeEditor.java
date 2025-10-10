package com.simon.tdd.trie;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 基于Java+Swing+前缀树 实现了一个简单的编辑器代码提示工具
 * https://leetcode.cn/problems/implement-trie-prefix-tree/solutions/717239/shi-xian-trie-qian-zhui-shu-by-leetcode-ti500/comments/3114948/
 *
 */
class TrieNodee {
    private final TrieNodee[] children = new TrieNodee[26];
    private boolean isEnd;

    TrieNodee getChild(char ch) {
        return children[ch - 'a'];
    }

    void setChild(char ch, TrieNodee node) {
        children[ch - 'a'] = node;
    }

    boolean isEnd() {
        return isEnd;
    }

    void setEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }
}

class Trie {
    private final TrieNodee root = new TrieNodee();

    void insert(String word) {
        TrieNodee node = root;
        for (char ch : word.toLowerCase().toCharArray()) {
            if (ch < 'a' || ch > 'z') continue;
            if (node.getChild(ch) == null) {
                node.setChild(ch, new TrieNodee());
            }
            node = node.getChild(ch);
        }
        node.setEnd(true);
    }

    List<String> findWordsWithPrefix(String prefix) {
        List<String> results = new ArrayList<>();
        TrieNodee node = searchPrefix(prefix);
        if (node != null) {
            findAllWords(node, prefix, results);
        }
        return results;
    }

    private TrieNodee searchPrefix(String prefix) {
        TrieNodee node = root;
        for (char ch : prefix.toLowerCase().toCharArray()) {
            if (ch < 'a' || ch > 'z') return null;
            if (node.getChild(ch) == null) return null;
            node = node.getChild(ch);
        }
        return node;
    }

    private void findAllWords(TrieNodee node, String prefix, List<String> results) {
        if (node.isEnd()) {
            results.add(prefix);
        }
        for (char c = 'a'; c <= 'z'; c++) {
            if (node.getChild(c) != null) {
                findAllWords(node.getChild(c), prefix + c, results);
            }
        }
    }
}

public class CodeEditor extends JFrame {
    private final JTextArea textArea = new JTextArea();
    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private final JList<String> suggestionList = new JList<>(listModel);
    private final JWindow suggestionWindow = new JWindow(this);
    private final Trie trie = new Trie();
    private int caretPosition;
    private String currentWord = "";

    public CodeEditor() {
        // 初始化 Trie
        for (String word : new String[]{
                "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class",
                "const", "continue", "default", "do", "double", "else", "enum", "extends", "final",
                "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", "int",
                "interface", "long", "native", "new", "package", "private", "protected", "public",
                "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this",
                "throw", "throws", "transient", "try", "void", "volatile", "while", "_",
                // java.lang
                "Object", "String", "StringBuilder", "StringBuffer", "Integer", "Double", "Float",
                "Long", "Short", "Byte", "Boolean", "Character", "Math", "System", "Class",
                "Thread", "Runtime", "Process", "ProcessBuilder", "Exception", "Error", "Throwable",
                "Enum", "ClassLoader", "SecurityManager",
                // java.util
                "ArrayList", "LinkedList", "HashMap", "HashSet", "TreeMap", "TreeSet",
                "LinkedHashMap", "LinkedHashSet", "Collections", "Arrays", "Date", "Calendar",
                "GregorianCalendar", "Scanner", "Random", "UUID", "Optional", "List", "Set", "Map",
                "Queue", "Deque",
                // java.io
                "File", "InputStream", "OutputStream", "Reader", "Writer", "BufferedReader",
                "BufferedWriter", "FileReader", "FileWriter", "PrintStream", "PrintWriter",
                "RandomAccessFile",
                // java.math
                "BigInteger", "BigDecimal",
                // java.net
                "URL", "URLConnection", "Socket", "ServerSocket", "HttpURLConnection", "InetAddress",
                // java.text
                "DateFormat", "SimpleDateFormat", "NumberFormat", "DecimalFormat",
                // java.time
                "LocalDate", "LocalTime", "LocalDateTime", "ZonedDateTime", "Instant", "Duration", "Period"
        }) {
            trie.insert(word);
        }

        // 设置主窗口
        setTitle("Code Editor");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // 初始化建议列表
        suggestionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        suggestionList.setVisibleRowCount(5);
        suggestionList.setFocusable(false);
        suggestionWindow.add(new JScrollPane(suggestionList));
        suggestionWindow.setSize(200, 100);

        // 监听文本变化
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                handleInput();
            }

            public void removeUpdate(DocumentEvent e) {
                handleInput();
            }

            public void changedUpdate(DocumentEvent e) {
                handleInput();
            }
        });

        // 监听光标位置
        textArea.addCaretListener(e -> {
            caretPosition = textArea.getCaretPosition();
            handleInput();
        });

        // 鼠标选择
        suggestionList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1 && suggestionList.getSelectedValue() != null) {
                    replaceWord(suggestionList.getSelectedValue());
                    suggestionWindow.setVisible(false);
                    textArea.requestFocus();
                }
            }
        });

        // 键盘导航
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!suggestionWindow.isVisible()) return;
                int idx = suggestionList.getSelectedIndex();
                if (e.getKeyCode() == KeyEvent.VK_DOWN && idx < listModel.size() - 1) {
                    suggestionList.setSelectedIndex(idx + 1);
                    e.consume();
                } else if (e.getKeyCode() == KeyEvent.VK_UP && idx > 0) {
                    suggestionList.setSelectedIndex(idx - 1);
                    e.consume();
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER && idx >= 0) {
                    replaceWord(suggestionList.getSelectedValue());
                    suggestionWindow.setVisible(false);
                    e.consume();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    suggestionWindow.setVisible(false);
                    e.consume();
                }
            }
        });
    }

    private void handleInput() {
        SwingUtilities.invokeLater(() -> {
            try {
                String text = textArea.getText();
                currentWord = getCurrentWord(text, caretPosition);
                if (currentWord.isEmpty()) {
                    suggestionWindow.setVisible(false);
                    return;
                }

                listModel.clear();
                for (String suggestion : trie.findWordsWithPrefix(currentWord)) {
                    listModel.addElement(suggestion);
                }

                if (!listModel.isEmpty()) {
                    suggestionList.setSelectedIndex(0);
                    Rectangle caretBounds = textArea.modelToView(caretPosition);
                    if (caretBounds != null) {
                        Point caretPos = new Point(caretBounds.x, caretBounds.y + textArea.getFont().getSize());
                        SwingUtilities.convertPointToScreen(caretPos, textArea);
                        suggestionWindow.setLocation(caretPos.x, caretPos.y);
                        suggestionWindow.setVisible(true);
                    }
                } else {
                    suggestionWindow.setVisible(false);
                }
            } catch (Exception e) {
                suggestionWindow.setVisible(false);
            }
        });
    }

    private String getCurrentWord(String text, int caretPos) {
        int start = caretPos - 1;
        while (start >= 0 && Character.isLetter(text.charAt(start))) {
            start--;
        }
        return start + 1 < caretPos ? text.substring(start + 1, caretPos).toLowerCase() : "";
    }

    private void replaceWord(String selectedWord) {
        try {
            String text = textArea.getText();
            int start = caretPosition - currentWord.length();
            textArea.setText(text.substring(0, start) + selectedWord + text.substring(caretPosition));
            textArea.setCaretPosition(start + selectedWord.length());
        } catch (Exception ignored) {
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CodeEditor().setVisible(true));
    }
}
