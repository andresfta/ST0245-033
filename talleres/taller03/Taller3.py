def towerOfHannoiAux(n, beg, aux, end):
    if n == 1:
        print(str(beg) + "→" + str(end))
    else:
        towerOfHannoiAux(n - 1, beg, end, aux)
        towerOfHannoiAux(1    , beg, aux, end)
        towerOfHannoiAux(n - 1, aux, beg, end)

def towerOfHannoi(n):
    towerOfHannoiAux(n, 1, 2, 3)

##towerOfHannoi(5)

def combinations(s):
    combinationsAux("", s)

def combinationsAux(base, s):
    if len(s) == 0:
        print(base)
    else:
        combinationsAux(base + s[0], s[1:])
        combinationsAux(base, s[1:])

##combinations("abc")

def permutations(s):
    permutationsAux("", s)

def permutationsAux(base, s):
    if len(s) == 0:
        print(base)
    else:
        for i in range(len(s)):
            permutationsAux(base + s[i], s[:i] + s[i + 1:])
    
##permutations("abcd")

def permutations4(s):
    permutations4Aux(s, "", len(s), len(s))

def permutations4Aux(s, prefix, long, count):
    if count == 1:
        for i in range(long):
            print(prefix + s[i])
    else:
        for i in range(long):
            permutations4Aux(s, prefix + s[i], long, count - 1)

##permutations4("abc")

def floodFill(m, i, j, originalColor, replacementColor):
    if m[i][j] == originalColor:
        m[i][j] = replacementColor
        if i != 0:
            floodFill(m, i - 1, j, originalColor, replacementColor)
        if j != 0:
            floodFill(m, i, j - 1, originalColor, replacementColor)
        if i < len(m) - 1:
            floodFill(m, i + 1, j, originalColor, replacementColor)
        if j < len(m) - 1:
            floodFill(m, i, j + 1, originalColor, replacementColor)

m = []
for i in range(10):
    m.append([0] * 10)

for row in m:
    print row

floodFill(m, 3, 3, 0, 1)

for row in m:
    print row
