#include <iostream>
#include "include/PropertyTest.h"

using namespace std;

int main()
{
    PropertyTest* test = new PropertyTest;
    test->count = 5;    //-- call the set method --

    cout << test->count << endl;     //-- call the get method --
    return 0;
}
