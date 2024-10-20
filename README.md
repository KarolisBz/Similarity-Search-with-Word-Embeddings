# Similarity Search with Word Embeddings

**Author:** Karolis Bandziulis  
**Version:** Java 17  

## Description
This is a console-based application that generates a file with 'n' results of the most similar words. The results are based on comparing word vectors from a user-specified embeddings file using one of the selected algorithms: **Dot Product**, **Euclidean Distance**, or **Cosine Distance**.

Written for Data Structures and algorithms 2nd year project and a gateway to AI innerworkings.

## To Run
From the console at the `.jar` file directory:

1. Run: `java -cp ./dsa.jar ie.atu.sw.Runner`
2. Select option 1 and set the word-embeddings file to a valid file path.
3. Select option 2 and set the output file path to the desired location.
4. If you choose an invalid path, you must start from step 1 again.
5. If you do not select anything for option 2, it will use a default name `./out.txt`.
6. Use option 4 to configure how many output results (n) you want.
7. Use option 5 to configure the order of your output.
8. Use option 6 to choose which comparing algorithm you want to use.
9. Select option 3 and enter a word or short sentence; it will output the key name and similarity score as configured into the output file.
10. Select option 7 to exit the application.

## Features
- **Specify Embedding File**
  - Input the text/path of the file including the extension.
  
- **Specify an Output File**
  - Input the output file path including the extension.
  - Default: `./out.txt`
  
- **Enter a Word or Text**
  - Returns a message if no embeddings file is selected.
  - Allows the user to enter a word or short sentence and compares it with the selected algorithm.
  - Outputs sorted string comparison similarity values as configured.
  
- **Configure Options**
  - Select 'n', the number of outputs you want.
  
- **Change Output Order**
  - Select between descending and ascending.
  
- **Change Comparing Algorithm**
  - Select between **Dot Product**, **Euclidean Distance**, or **Cosine Distance**.
