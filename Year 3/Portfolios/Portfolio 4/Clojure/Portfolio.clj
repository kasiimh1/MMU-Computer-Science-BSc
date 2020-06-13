;; task 1
(defn get-divisors [n]
  (range 2 (inc (Math/floor(Math/sqrt n)))))

;; task 2
(defn divides? [x n]
  (== (mod n x ) 0 ))

;; task 3
(defn no-divisors? [n]
  (not-any? (fn [x] (divides? x n )) (get-divisors n)))

;; task 4
(defn is-prime? [n]
	(if (== n 1)
		false
		(no-divisors? n)))

;; task 5
(defn prime-seq [from to]
  (filter is-prime? (range from (inc to))))

;; task 6
(defn print-top-primes [from to]
  (def seq (reverse (prime-seq from to )))
  (let [ttl (reduce + seq )]
  (println "sequence: " seq)
  (println "total: " ttl)))

;;test
(println "task 1")
(println (get-divisors 4)) ;; returns 2
(println (get-divisors 101)) ;; returns 2 3 4 5 6 7 8 9 10 
(println "task 2")
(println (divides? 2 10)) ;; true 
(println (divides? 4 10)) ;; false
(println "task 3")
(println (no-divisors? 9)) ;; false
(println (no-divisors? 7)) ;; true
(println "task 4")
(println (is-prime? 1)) ;; false
(println (is-prime? 2)) ;; true
(println (is-prime? 3)) ;; true
(println (is-prime? 4)) ;; false
(println (is-prime? 101)) ;; true
(println "task 5")
(println (prime-seq 50 100)) ;; (53 59 61 67 71 73 79 83 89 97)
(println (prime-seq 7 11)) ;; (7 11)
(println "task 6")
(print-top-primes 50 100) ;; 732
(print-top-primes 7 11) ;; 18
