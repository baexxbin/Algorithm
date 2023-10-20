import sys

input = sys.stdin.readline


def fee2elect(price):
    if price <= 200:
        return price // 2
    elif price <= 29900:
        return (price - 200) // 3 + 100
    elif price <= 4979900:
        return (price - 29900) // 5 + 10000
    else:
        return (price - 4979900) // 7 + 1000000


def elect2fee(elect):
    if elect <= 100:
        return elect * 2
    elif elect <= 10000:
        return 200 + (elect - 100) * 3
    elif elect <= 1000000:
        return 29900 + (elect - 10000) * 5
    else:
        return 4979900 + (elect - 1000000) * 7


if __name__ == '__main__':

    while True:
        a, b = map(int, input().split())

        if a == 0 and b == 0:
            break

        total_elect = fee2elect(a)
        mn = 0
        mx = total_elect // 2

        while mn <= mx:
            mid = (mn + mx) // 2

            s_fee = elect2fee(mid)
            n_fee = elect2fee(total_elect - mid)

            diff = abs(s_fee - n_fee)

            if diff < b:
                mx = mid - 1
            elif diff > b:
                mn = mid + 1
            else:
                print(s_fee)
                break

# 200: 2원으로 낼 수 있는 최대값 (2*100)
# 29900 : (100*2)+(9900*3), 3원으로 낼 수 있는 최대값(처음 100까지는 2원으로 냄)
# 4979900 : (100*2)+(9900*3)+(990000*5)
