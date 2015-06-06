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

#include "../include/LibraryLoader.h"

LibraryLoader* LibraryLoader::m_instance = 0;

LibraryLoader::LibraryLoader()
{
    //ctor
}

LibraryLoader::~LibraryLoader()
{
    //dtor
}

LibraryLoader* LibraryLoader::getInstance()
{
    if(m_instance == 0)
    {
        m_instance = new LibraryLoader();
    }
    return m_instance;
}

void* LibraryLoader::loadLibrary(string name)
{
    #ifdef __unix__
        name += ".so";
        m_library = dlopen(name.c_str(), RTLD_NOW);
    #elif defined(_WIN32) || defined(WIN32)
        name += ".dll";
        m_library = (void*) LoadLibrary(name.c_str());
    #endif // defined

    return m_library;
}

void* LibraryLoader::getExternalFunction(string name)
{
    #ifdef __unix__
        m_method = dlsym(m_library, name.c_str());
    #elif defined(_WIN32) || defined(WIN32)
        m_method = (void*) GetProcAddress((HINSTANCE)m_library, name.c_str());
    #endif // defined

    return m_method;
}

bool LibraryLoader::freeLibrary()
{
    #ifdef __unix__
        m_freedom = dlclose(m_library);
    #elif defined(_WIN32) || defined(WIN32)
        m_freedom = FreeLibrary((HINSTANCE)m_library);
    #endif // defined

    return m_freedom;
}
