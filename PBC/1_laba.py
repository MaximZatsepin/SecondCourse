import random

x0 = 1
y0 = 2
r0 = 5

def result (x0, y0, r0, n):
    x_min = x0 - r0
    x_max = x0 + r0
    y_min = y0 - r0
    y_max = y0 + r0

    m = 0
    D = 161/1000
    x = (x_max - x_min) * D + x_min
    # print(x)
    D = 676/1000
    y = (y_max - y_min) * D + y_min
    # print(y)
    # for i in range(n):
    if (((x - x0)**2) + ((y - y0) ** 2)) <= r0**2:
        m += 1
    # print((((x - x0)**2) + ((y - y0) ** 2)))
    # s = m/n *4
    # print(s)
    print(m)




result(x0, y0, r0, n = 1000)