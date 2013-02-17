#include <string>
#include <iostream>

#include "smart_ptr.hpp"

class Der
{
    int info;
    std::string s;
public:
    Der(int i) : info(i), s ("memory dpike") {}
    ~Der() {std::cout << "Der::~Der" << std::endl;}

    int getVal() { return info;}
    void setVal (int i) {info = i;}

};
