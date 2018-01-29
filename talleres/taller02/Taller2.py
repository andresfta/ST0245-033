def gcd(p,q):
    if p % q == 0: return q
    return gcd(q,p%q)

def p2(index, nums, target):
    if index == len(nums): return target == 0
    if p2(index + 1, nums, target): return True
    if p2(index + 1, nums, target - nums[index]): return True
    return False

def combinations(s):
    combinationsAux("", s)

def combinationsAux(base, s):
    if(len(s) == 0): print base
    else:
        combinationsAux(base + s[0], s[1:])
        combinationsAux(base, s[1:])

##print mcd(102,68)
##print p2(0,[1,2,3],7)
##combinations("abcd")
