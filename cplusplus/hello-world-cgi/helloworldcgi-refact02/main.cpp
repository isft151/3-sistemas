/**
 *  Copyright 2015 Gabriel Nicolás González Ferreira <gabrielinuz@gmail.com>
 *
 *  Permission is hereby granted, free of charge, to any person obtaining
 *  a copy of this software and associated documentation files (the
 *  "Software"), to deal in the Software without restriction, including
 *  without limitation the rights to use, copy, modify, merge, publish,
 *  distribute, sublicense, and/or sell copies of the Software, and to
 *  permit persons to whom the Software is furnished to do so, subject to
 *  the following conditions:
 *
 *  The above copyright notice and this permission notice shall be
 *  included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 *  MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 *  LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 *  OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 *  WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
**/

#include <iostream>
#include <vector>
#include <map>
#include <string>
#include <sstream>//FOR EXPLODE

using namespace std;

//DECODE URI FORMAT
string urlDecode(string &SRC)
{
    string ret;
    char ch;
    int i, ii;
    for (i=0; i<SRC.length(); i++)
    {
        if (int(SRC[i])==37)
        {
            sscanf(SRC.substr(i+1,2).c_str(), "%x", &ii);
            ch=static_cast<char>(ii);
            ret+=ch;
            i=i+2;
        } else
        {
            ret+=SRC[i];
        }
    }
    return (ret);
}

//DIVIDE A STRING BY A CHAR DELIMITER
vector<string> explode(string const &input, char delimiter)
{
    vector<string> result;
    istringstream iss(input);

    for (string token; getline(iss, token, delimiter); )
    {
        result.push_back(move(token));
    }

    return result;
}

//GET HTTP REQUEST
string getRequestString()
{
    string requestString = "";

    if(getenv("REQUEST_METHOD") == NULL)
    {
        cout << "The request method is null" << endl;
        exit(-1);
    }
    else
    {
        string request_method = string(getenv("REQUEST_METHOD"));

        if(request_method == "GET") { requestString = string(getenv("QUERY_STRING")); }

        if(request_method == "POST") { cin >> requestString; }
    }

    return requestString;
}

//GENERATE REQUEST MAP
map<string, string> getRequestMap()
{
    string requestString = getRequestString();
    vector<string> keysWithValues = explode(requestString, '&');
    vector<string> keysAndValues;
    vector<string>::iterator i;

    int aux = 0;
    map<string, string> requestMap;

    for(i = keysWithValues.begin(); i != keysWithValues.end(); i++)
    {
        keysAndValues = explode(keysWithValues[aux], '=');
        requestMap[keysAndValues[0]] = keysAndValues[1];
        aux++;
    }

    return requestMap;
}

//MAIN FUNCTION
int main()
{
    cout << "Content-type:text/html; charset=UTF8\r\n\r\n";
    cout << "<!DOCTYPE html>\n";
    cout << "<html>\n";
    cout << "<head>\n";
    cout << "<title>Hello World - Third CGI Program</title>\n";
    cout << "<meta charset='utf-8'>\n";
    cout << "</head>\n";
    cout << "<body>\n";

    cout << "<form action='helloworld.exe' method='POST'>";
    cout << "<label>Introduzcá su nombré:</label>\n";
    cout << "<input type='text' name='txt1' id='txt1'>\n";
    cout << "<input type='submit' name='sbmt1' value='enviar'>\n";
    cout << "</form>\n";

    map<string, string> requestMap = getRequestMap();

    cout << "<h2>Hello! " << urlDecode(requestMap["txt1"]) << " This is my third CGI program</h2>\n";

    cout << "</body>\n";
    cout << "</html>\n";
    return 0;
}
