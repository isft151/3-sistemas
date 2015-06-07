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

#include "../include/PluginFactory.h"

PluginFactory::PluginFactory()
{
    //ctor
}

PluginFactory::~PluginFactory()
{
    //dtor
}

IPlugin* PluginFactory::createFrom(string path)
{
    IPlugin* plugin = NULL;
    //THE LOADER:
    ILibraryLoader* loader = LibraryLoader::getInstance();

    //LOAD
    void* load = loader->loadLibrary(path);
    if(load)
    {
        typedef IPlugin* ( *PluginFactory ) ();
        PluginFactory factory = ( PluginFactory ) loader->getExternalFunction( "create" );
        if ( factory )
        {
            if (plugin = factory()){}
            else
            {
                loader->freeLibrary();
                cout << "Error:  Failed creating a plugin from "
                        << path << ", null plugin." << endl;
                exit(-1);
            }
        }
        else
        {
            loader->freeLibrary();
            cout << "Error:  Failed creating a plugin from "
                    << path << ", there is no external function \"create(void)\"." << endl;
            exit(-1);
        }
    }
    else
    {
        cout << "Error: Failed to load the library: " << path << endl;
        exit(-1);
    }
    return plugin;
}


