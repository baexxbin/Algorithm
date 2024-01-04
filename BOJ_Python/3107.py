import sys

input = sys.stdin.readline

if __name__ == '__main__':
    ipv6 = input().strip().split(':')

    # ans = ''
    # flag = False
    # for unit in ipv6:
    #     if unit=='':
    #         if flag:
    #             ans+='0000:'
    #         else:
    #             ans+='0000:'*(8-len(ipv6)+1)
    #             flag = True
    #         continue
    #     if len(unit) < 4:
    #         ans+='0'*(4-len(unit))
    #     ans+=unit
    #     ans+=':'
    # print(ans[:-1])

    ans = []
    flag = False
    for unit in ipv6:
        if unit == '' and not flag:
            ans += ['0000' for _ in range(8-len(ipv6)+1)]
            flag = True
        else:
            ans.append(unit.zfill(4))
    print(':'.join(ans))