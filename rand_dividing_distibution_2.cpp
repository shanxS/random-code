// dividing rand o/p distribution
// based on chnaces
// eg if chOne = 1 and chTwo = 2, chTwo will be o/p 
// (on average) twice as many times as chOne


#include <iostream>
#include <cstdlib>
#include <ctime>

int main()
{
  const int nrolls=10000;  // number of experiments
  const int nstars=100;    // maximum number of stars to distribute
  
  srand (time (NULL));

  
    int chOne = 1, chTwo = 2;
    float sum = (float)(chOne + chTwo);
    float multiplier = 100.0/sum;
    float bar = chOne*multiplier/100.0;
    std::cout << "\n BAR " << bar <<  " chOne/chTwo " << ((float)chOne/(float)chTwo) << std::endl;
  
  int p[6]={};

  for (int i=0; i<nrolls; ++i) {
    int i = rand();
    if (i < RAND_MAX * bar)
         ++p[0];
    else
         ++p[1];         
         
  }

  for (int i=0; i<6; ++i) {
    std::cout << i << "-" << (i+1) << ": ";
    std::cout << std::string(p[i]*nstars/nrolls,'*') << std::endl;
  }
  
  std::cout << "\n " << p[0] << " " << p[1];

  return 0;
}
