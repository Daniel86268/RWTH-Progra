-- a
mult :: Int -> Int -> Int
mult _ 0 = 0
mult x y = x + mult x (y-1) 

-- b
bLog :: Int -> Int
bLog 1 = 0
bLog x = bLogH 2 1
  where
    bLogH :: Int -> Int -> Int
    bLogH y r | x > y     = bLogH (y*2) (r+1)
              | otherwise = r

-- c
getLastTwo :: [Int] -> [Int]
getLastTwo [x, y]   = [x, y]
getLastTwo (_:x:xs) = getLastTwo (x:xs)

-- d
singletons :: Int -> [[Int]]
singletons 0 = []
singletons n = [n] : singletons (n-1)

-- e
packing :: [[Int]] -> [Int] -> [[Int]]
packing []      _      = []
packing xs      []     = xs
packing ([]:xs) (y:ys) = [y] : packing xs ys
packing (x:xs)  (y:ys) = x   : packing xs (y:ys)

-- f
listAdd :: Int -> [Int] -> [Int]
listAdd _ []     = []
listAdd z (x:xs) = (z+x) : listAdd x xs
