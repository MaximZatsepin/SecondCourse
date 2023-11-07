def GetArg(F,minArg,maxArg,Value,eps):
    minVal = F(minArg)
    maxVal = F(maxArg)
    while abs((maxArg-minArg)/maxArg) > eps:
        midArg = (maxArg+minArg) / 2
        midVal = F(midArg)
        if midVal > Value:
            maxArg = midArg
            maxVal = midVal
        if midVal <= Value:
            minArg = midArg
            minVal = midVal
    return ((minArg+maxArg) / 2)


def gettabF(f,minarg,maxarg,pointscount):
    minval = f(minarg)
    maxval = f(maxarg)
    dval = (maxval-minval) / (pointscount-1)
    i = 0
    ytab = []
    xtab = []
    ytab[i] = minval
    xtab[i] = minarg
    for i in range(1,pointscount-2):
        ytab[i] = minval + dval * i
        xtab[i] = GetArg(f,minarg,maxarg,ytab,10**-15)
    ytab[pointscount-1] = maxval
    xtab[pointscount-1] = maxarg
    res = []
    res[0] = xtab
    res[1] = ytab
    return res