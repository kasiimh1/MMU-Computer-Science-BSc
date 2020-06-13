import Data.Char
import Data.List
import Data.Function

-- your code here

dictionaryCommonWords = ["the","be","to","of","and","a","in","that","have","I","it","for","not","on","with","he","as","you","do","at"]
    
toWordList :: String -> [String]
toWordList string = words $ filter(\str -> elem str('\n':' ':['a'..'z'])) $ map toLower string 

countCommonWords :: [String] -> Int 
countCommonWords array = length $ filter (\str -> elem str dictionaryCommonWords) array

dropCommonWords :: [String] -> [String]
dropCommonWords array = filter (\str -> not (elem str dictionaryCommonWords)) array

countWords :: [String] -> [(String, Int)]
countWords array = map (\cmnGrp -> (head cmnGrp, length cmnGrp)) $ group $ sort array

sortWords :: [(String, Int)] -> [(String, Int)] 
sortWords cmnGrp = reverse $ sortBy (on compare snd) cmnGrp

makeHistogramLine :: (String, Int) -> String
makeHistogramLine common = (concat $ replicate (snd common) "*") ++ (" -> " ++ ((fst common) ++ "\n"))

makeHistogram :: [(String, Int)] -> String
makeHistogram common = concat $ map makeHistogramLine $ take 20 common

text = "It was the best of times, it was the worst of times, it was the age of wisdom, it was the age of foolishness, it was the epoch of belief, it was the epoch of incredulity, it was the season of Light, it was the season of Darkness, it was the spring of hope, it was the winter of despair, we had everything before us, we had nothing before us, we were all going direct to Heaven, we were all going direct the other way--in short, the period was so far like the present period, that some of its noisiest authorities insisted on its being received, for good or for evil, in the superlative degree of comparison only.\nThere were a king with a large jaw and a queen with a plain face, on the throne of England; there were a king with a large jaw and a queen with a fair face, on the throne of France. In both countries it was clearer than crystal to the lords of the State preserves of loaves and fishes, that things in general were settled for ever."

main = do
  let wordlist = toWordList text
  putStrLn "Report:"
  putStrLn ("\t" ++ (show $ length wordlist) ++ " words")
  putStrLn ("\t" ++ (show $ countCommonWords wordlist) ++ " common words")
  putStrLn "\nHistogram of the most frequent words (excluding common words):\n"
  putStr $ makeHistogram $ sortWords $ countWords $ dropCommonWords $ wordlist
