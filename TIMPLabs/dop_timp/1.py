A = [1,6,11,15,25]
B = []
for i in A:
    for j in A:
        B.append([i,j])
print(B)
for i in B:
    if (i[0]-i[1]) % 5 == 0 and (i[0]-i[1]) > 0:
        print(i)