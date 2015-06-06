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
#include <stdlib.h>
#include <string>
#include "include/PluginFactory.h"
#include "include/IPlugin.h"
#include "include/IGreeter.h"

using namespace std;

int main()
{
    IPlugin* greeter_plugin = PluginFactory::createFrom("./lib/libgreeter");
    if(greeter_plugin->implements("IGreeter"))
    {
        IPlugin* messenger_plugin = PluginFactory::createFrom("./lib/libmessenger");
        IGreeter* greeter = (IGreeter*) greeter_plugin->getInstance();
        greeter->setMessenger(messenger_plugin);
        greeter->greet("Hello World!");
    }
    else
    {
        cout << "Error: The plugin doesn't implement the IGreeter interface!" << endl;
        exit(-1);
    }
    greeter_plugin->release();
    return 0;
}
