'''
Найти число пи по формуле из тетради
10^2, 10^3, 10^4, 10^5, 10^6
Найти погрешность каждого (pi` - pi / pi)

График погрешности от количества экспериментов

'''
from random import *

def calc_pi(x0,y0,r0,n):
    Xmin = x0 - r0
    Xmax = x0 + r0
    Ymin = y0 - r0
    Ymax = y0 + r0
    m = 0
    for i in range(1,n):
        p = randint(0,1)
        Xp = (Xmax - Xmin) * p + Xmin
        p = randint(0,2)
        Yp = (Ymax - Ymin) * p + Ymin
        if (((Xp - x0) ** 2 + (Yp - y0) ** 2) < (r0**2)) :
            m += 1
            
    S = m / n * 4
    return S

print(calc_pi(1,2,5,100000))

