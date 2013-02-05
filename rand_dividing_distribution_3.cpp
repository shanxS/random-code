// getRandomNum (int bins), get random o/p
// in semi open range [0, bins)
// o/p distribution will not be 'perfectly normal' if RAND_MAX % bins != 0

#include <iostream>
#include <cstdlib>
#include <ctime>

float getRandomNum (int bins)
{
    int ran = rand();
    int div = RAND_MAX/bins;
    int retVal = ran/div;
    
    // Since bins might not divide output range of rand() evenly.
    // Of course, distribution will not be 'perfectly' uniform in this case
    if (retVal >= bins)
        retVal = bins-1;
    
    return retVal;
}

int main()
{
  const int nrolls=10000;  // number of experiments
  const int nstars=100;    // maximum number of stars to distribute
  
  srand (time (NULL));

  
    
  int p[6]={};

  for (int k=0; k<nrolls; ++k) {
    float i = getRandomNum (4);
    if (i < 1)
         ++p[0];
    else if (i>=1 && i<2)
        ++p[1];
    else if (i >= 2 && i<3)
         ++p[2];         
    else if (i>=3)
         ++p[3];
     else
        ++p[4]; // to grap spuriours o/p
         
  }

  for (int i=0; i<6; ++i) {
    std::cout << i << "-" << (i+1) << ": ";
    std::cout << std::string(p[i]*nstars/nrolls,'*') << std::endl;
  }
  
  std::cout << "\n " << p[0] << " " << p[1];

  return 0;
}
