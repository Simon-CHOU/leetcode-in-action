package com.simon.interview;

public class AlternatePrinting {
    private static final int MAX_NUMBER = 100;
    private int currentNumber = 0;
    private final Object lock = new Object();
    private boolean isEvenThreadTurn = true;

    public static void main(String[] args) {
        AlternatePrinting alternatePrinting = new AlternatePrinting();
        Thread evenThread = new Thread(alternatePrinting::printEvenNumbers);
        Thread oddThread = new Thread(alternatePrinting::printOddNumbers);
        evenThread.start();
        oddThread.start();
    }

    private void printEvenNumbers() {
        while (currentNumber <= MAX_NUMBER) {
            synchronized (lock) {
                if (isEvenThreadTurn) {
                    System.out.println(currentNumber);
                    currentNumber++;
                    isEvenThreadTurn = false;
                    lock.notifyAll();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }

    private void printOddNumbers() {
        while (currentNumber < MAX_NUMBER) {
            synchronized (lock) {
                if (!isEvenThreadTurn) {
                    System.out.println(currentNumber);
                        currentNumber++;
                    isEvenThreadTurn = true;
                    lock.notifyAll();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }
}
