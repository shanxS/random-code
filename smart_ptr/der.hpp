#include "smart_ptr.hpp"

class Der
{
    int info;
public:
    Der(int i) : info(i), m_pNext(NULL) {}
    ~Der() {cout << "Der::~Der" << endl;}
    int getVal() { return info;}
    Smart_ptr<Der> m_pNext;
};
