#include <iostream>

#include "smart_ptr.hpp"
#include "der.hpp"

using namespace std;

int main()
{
   
/*
    // Memory always leaks    
    Smart_ptr<Der> m (new Der(1));
    m->m_pNext = m;
*/
    
/*
    Smart_ptr<Der> m (new Der(1));
    Smart_ptr<Der> m2 (m);
    Smart_ptr<Der> m3;
    m3 = m;
    //testFunc();
    cout << "value is " << m2.getVal() << endl;
    cout << "value is " << m2->getVal() << endl;
    cout << "value is " << (*m2).getVal() << endl;
*/
    return 0;
}
