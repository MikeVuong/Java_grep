import java.io.*;
import java.net.*;


/* Commands used to test this code was 
javac SourceViewer.java
java SourceViewer https://positivenessunlimited.com/the-power-of-positive-thinking-a-short-article/ The

*/

public class SourceViewer {

  public static void main (String[] args) {

    // Used to keep a count of how much characters were in the string
    int count = 0;
    // Used to keep track of which line the searched string was found on
    int lineCount = 1;
    // Usd to store the found string
    StringBuilder s = new StringBuilder();

    // Only run if there are user inputted arguments
    if (args.length > 0) {
      InputStream in = null;
      try {
        // Open the URL for reading
        URL u = new URL(args[0]);
        in = u.openStream();
        // buffer the input to increase performance 
        in = new BufferedInputStream(in);       
        // chain the InputStream to a Reader
        Reader r = new InputStreamReader(in);
        int c;
        // Creates the string buffer to be searched,
        // Reads until it hits a new line
        while ((c = r.read()) != -1) {
          // Adding a 100 character limit to keep tidy
          if(count >= 100)
          {
            // If there are over 100 characters in the line stop copying to organize output
            c = 10;
          }
          // If the character is not a new line insert into the string builder
          if(c != 10)
          {
            s.insert(count,(char)c);
            // Count increases based on the amount of characters in the string
            count++;
          }else{
            // Checks if the searched string is in the line
            // Prints if it is 
            if(s.indexOf(args[1]) != -1)
            {
              // Used for deleting leading white spaces
              for(int i = 0; i < s.length(); i++)
              {
                // Counts until the character is neither a white space or a tab
                if(s.charAt(i) != 9 && s.charAt(i) != 32)
                {
                  // Once it finds a non white space/tabbed character it deletes 
                  // Based on it's index, removing all leading white spaces
                  s.delete(0,i);
                  // Breaks the loop after to indicate that all leading whitespace has been removed
                  break;
                }
              }
              // Prints out the line # and the string containing the user inputted search
              System.out.println(lineCount + ": " + s);
            }
            // Deletes the previous buffer for new line
            s.delete(0,s.length());
            // Resets the count for the new line
            count = 0;
            // Variable to keep track on which line the string was located
            lineCount++;
          }
        }
      } catch (MalformedURLException ex) {
        System.err.println(args[0] + " is not a parseable URL");
      } catch (IOException ex) {
        System.err.println(ex);
      } finally {
        if (in != null) {
          try {
            in.close();
          } catch (IOException e) {
            // ignore
          }
        }
      }
    }
  }
} 