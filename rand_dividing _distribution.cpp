// dividing the uniform distribution o/p
// in equal range and testing it

#include <iostream>
#include <cstdlib>
#include <ctime>

int main()
{
  const int nrolls=10000;  // number of experiments
  const int nstars=100;    // maximum number of stars to distribute
  
  srand (time (NULL));

  int p[10]={};

  for (int i=0; i<nrolls; ++i) {
    int i = rand();
    if (i > RAND_MAX/10 && i < 2*RAND_MAX/10)
         ++p[9];
    else if (i > 2*RAND_MAX/10 && i < 3*RAND_MAX/10)
         ++p[8];
    else if (i > 3*RAND_MAX/10 && i < 4*RAND_MAX/10)
         ++p[7];
    else if (i > 4*RAND_MAX/10 && i < 5*RAND_MAX/10)
         ++p[6];
    else if (i > 5*RAND_MAX/10 && i < 6*RAND_MAX/10)
         ++p[5];
    else if (i > 6*RAND_MAX/10 && i < 7*RAND_MAX/10)
         ++p[4];
    else if (i > 7*RAND_MAX/10 && i < 8*RAND_MAX/10)
         ++p[3];         
    else if (i > 8*RAND_MAX/10 && i < 9*RAND_MAX/10)
         ++p[2];         
    else if (i > 9*RAND_MAX/10 && i < 10*RAND_MAX/10)
         ++p[1];
    else if (i < RAND_MAX/10)
         ++p[0];         
         
  }

  for (int i=0; i<10; ++i) {
    std::cout << i << "-" << (i+1) << ": ";
    std::cout << std::string(p[i]*nstars/nrolls,'*') << std::endl;
  }

  return 0;
}
