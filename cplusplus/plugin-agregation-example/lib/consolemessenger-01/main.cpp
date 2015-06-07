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

#include "../../include/IPlugin.h"
#include "../../include/IMessenger.h"
#include <iostream>

using namespace std;

class ConsoleMessenger : public IMessenger, public IPlugin
{
    public:
        ConsoleMessenger();
        virtual ~ConsoleMessenger();
        void say(string message);

        bool implements(string interfaceName);
        void* getInstance();
        void release();

    private:
        int m_referenceCounter;
        bool m_implemented;
};

ConsoleMessenger::ConsoleMessenger() : m_referenceCounter(0) {}

ConsoleMessenger::~ConsoleMessenger(){}

void ConsoleMessenger::say(string message)
{
    cout << "I am the Messenger 01 and the message is: " << message << endl;
}

bool ConsoleMessenger::implements(string interfaceName)
{
    return (interfaceName == "IPlugin" || interfaceName == "IMessenger") ?
        m_implemented = true
            : m_implemented = false;
}

void* ConsoleMessenger::getInstance()
{
    if(m_implemented) {  m_referenceCounter++;  return this; }
    return NULL;
}

void ConsoleMessenger::release()
{
    m_referenceCounter--;
    if(m_referenceCounter <= 0) delete this;
}

extern "C" IPlugin* create();

IPlugin* create()
{
    return (IPlugin*) new ConsoleMessenger;
}
