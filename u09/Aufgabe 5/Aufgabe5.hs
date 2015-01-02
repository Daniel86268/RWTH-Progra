isEmpty :: [a] -> Int
isEmpty [] = 1
isEmpty _ = 0

removePairs :: [Int] -> [Int]
removePairs (x:y:xs) | x==y  	 =removePairs xs
					 | otherwise = x:(removePairs (y:xs))
removePairs x  					 = x


listSum :: [Int] -> Int
listSum [] = 0
listSum (x:xs) = x + (listSum xs)
								 