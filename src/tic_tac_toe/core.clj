(ns tic-tac-toe.core
  (:gen-class)
  (:require [clojure.string :as str]))

(defn get-O-input [board]
  (println "It's your turn O")
  (println "Enter the row and column you wanted to enter ")
  (let [input (read-line)
        row (Integer. (first (str/split input #",")))
        column (Integer. (last (str/split input #",")))
        value "O"]
    (println "The row and column you wanted to enter is" row column)
    (if (= (aget board (- row 1) (- column 1)) "-")
      [row column value]
      (do (println "You have entered the in the gap that was already entered. Please enter a new one")
          (get-O-input board)))))

(defn get-X-input [board]
  (println "It's your turn X")
  (println "Enter the row and column you wanted to enter ")
  (let [input (read-line)
        row (Integer. (first (str/split input #",")))
        column (Integer. (last (str/split input #",")))
        value "X"]
    (println "The row and column you wanted to enter is" row column)
    (if (= (aget board (- row 1) (- column 1)) "-")
       [row column value]
       (do (println "You have entered the in the gap that was already entered. Please enter a new one")
           (get-X-input board)))))

(defn initialize-board []
  (to-array-2d [["-" "-" "-"] ["-" "-" "-"] ["-" "-" "-"]]))

(defn update-board-with-values [board row column value]
  (aset board (Integer. row) (Integer. column) value)
  (dorun (do (for [x (range (count board))
                   y (range (count board))]
               (if (= y 2)
                 (do (print (aget board x y) " ") (newline))
                 (print (aget board x y) " ")))))
  board)

(defn check-row-side [board row column value]
  (if (and (= (aget board row 0) value)
           (= (aget board row 1) value)
           (= (aget board row 2) value))
    "TIC-TAC-TOE"

    (if (and (= (aget board 0 column) value)
             (= (aget board 1 column) value)
             (= (aget board 2 column) value))
      "TIC-TAC-TOE"
      false)))

(defn check-diagonal [board row column value]
  (if
    (or (= row column)
        (or (and (= row 0) (= column 2))
            (and (= row 0) (= column 2))))
    (if (and
          (= (aget board 0 0) value)
          (= (aget board 1 1) value)
          (= (aget board 2 2) value))
      "TIC-TAC-TOE"
      (if (and
            (= (aget board 0 2) value)
            (= (aget board 1 1) value)
            (= (aget board 2 0) value))
        "TIC-TAC-TOE"))))

(defn tic-tac-toe-check-for-O [board]
  (let [user-O-input (get-O-input board)
        row (- (first user-O-input) 1)
        column (- (get user-O-input 1) 1)
        value (last user-O-input)]
    (update-board-with-values board row column value)
    (println "UPDATED")
    (aget board (Integer. row) (Integer. column))
    (if (or (= (check-row-side board (Integer. row) (Integer. column) value) "TIC-TAC-TOE")
            (= (check-diagonal board row column value) "TIC-TAC-TOE"))
      1
      0)))

(defn tic-tac-toe-check-for-X [board]
  (let [user-X-input (get-X-input board)
        row (- (first user-X-input) 1)
        column (- (get user-X-input 1) 1)
        value (last user-X-input)]
    (update-board-with-values board row column value)
    (aget board (Integer. row) (Integer. column))
    (if (or (= (check-row-side board (Integer. row) (Integer. column) value) "TIC-TAC-TOE")
            (= (check-diagonal board row column value) "TIC-TAC-TOE"))
      1
      0)))

(defn find-the-winner [value-of-O]
  (if (= value-of-O 1)
    (println "Congrats O!!! You won the game")
    (println "Congrats X!!! You won the game")))

(defn -main
  [& args]
  (println "Hello, Welcome to Tic-tac-toe!")
  (let [board (initialize-board)
        a 0
        b 0
        count 0]
    (while (and (= a 0) (= b 0))
      (if (= count 8)
        (println "DRAW MATCH")
        (+ count 1))
      (def a (tic-tac-toe-check-for-O board))
      (if (= a 1)
          (def b 1)
          (def b (tic-tac-toe-check-for-X board))))
    (find-the-winner a)))


    ;(check-all-are-filled board))








