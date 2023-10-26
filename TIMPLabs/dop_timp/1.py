import random

# Задайте ваш алфавит
alphabet = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789._'

# Функция для проверки, что судоку решено правильно
def is_valid(board):
    for i in range(32):
        for j in range(32):
            if board[i][j] not in alphabet:
                return False
            if board[i].count(board[i][j]) > 1:
                return False
            if [board[x][j] for x in range(32)].count(board[i][j]) > 1:
                return False
    return True

# Функция для решения судоку
def solve_sudoku(board):
    for i in range(32):
        for j in range(32):
            if board[i][j] == '*':
                random.shuffle(list(alphabet))
                for char in alphabet:
                    board[i][j] = char
                    if is_valid(board) and solve_sudoku(board):
                        return True
                    board[i][j] = '*'
                return False
    return True

# Задайте вашу судоку
sudoku = '''
.0K**tY***ObV*HSBT*Ca******q***U
**e*P**xd*q**z*aV7**h*R***.*E***
**I*Z****f*v*****G*jr*i6p*zNX*co
uX**r****y****8*A*Ytx*l*F0v*9***
QpE***GC**D**w**L*e*H*****1g**Z*
f*s****Tp2k**mlIc**W**on****DBP*
**A*JD********E*w*0*K****4**btG*
*c********G*.3**D*y***qJuw***h6*
*3S**z*I**buX**t*****h**y**Zlq**
***D*Gl*k*A***J***x**TV*d*oM*_**
*6b*K*Cqi*Q****Z****w*_50*9**j**
***H**5j**N*d**p****A*n*R8**7L*m
4Z***1*ej******6**Uu*****G***T**
***_**i*B*9*q****H*oem**6***.***
**c**8E****0r***I**X**7BhV*Sn***
**R*u**Aa**z*****t***62ZC5JfWg**
A**5*CK******l*h*cR***tSJ**X*d**
***t**U**m0*fOVz*qi***T*v*KEg**u
vPl2xR*i3**5****Y**z***4***Q***Z
*h8k*V***g*NKa**oWQrp*s***7*L***
*WZz*****Xc***L*****B5*h*T**V*fs
3*r**H*B1*_**R******g*6I***y*N*O
******X***j*****_***8*a***q3mik*
*****p*4*7*n**.T**X****0**j*****
*Cq*******W**D*5ji*k*z*****u**Ot
I**bY***J*a1Qgx*******O*jE*8***P
**d****cZ*l**Uf***o*******e**Y*V
******2***t***4***h**ZW***BJK*b*
*VT*N*****v***s*Um****Iy**F***Xa
M*g*1*y*****S***X**L*Qp*_*******
*D*L*3F*K*******P2*A6**rc*pW****
***S**7*Vr**B**o****neG.***i****
'''

# Разделим строку на строки
sudoku_rows = sudoku.strip().split('\n')

# Преобразуем строки в двумерный массив
sudoku_grid = [list(row) for row in sudoku_rows]

# Решите судоку
if solve_sudoku(sudoku_grid):
    for row in sudoku_grid:
        print("".join(row))
else:
    print("Решения не существует.")
