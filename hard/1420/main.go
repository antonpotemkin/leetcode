package main

import (
	"fmt"
	"math"
)

func main() {
	fmt.Println(numOfArrays(2, 3, 1))
	fmt.Println(numOfArrays(5, 2, 3))
	fmt.Println(numOfArrays(9, 1, 1))
	fmt.Println(numOfArrays(50, 100, 25) == 34549172)
}

func numOfArrays(n int, m int, k int) int {
	cache := make([][][]int, 0)
	for i := 0; i < n; i++ {
		arrOuter := make([][]int, 0)
		for j := 0; j < m+1; j++ {
			arr := make([]int, 0)
			for h := 0; h < k+1; h++ {
				arr = append(arr, -1)
			}
			arrOuter = append(arrOuter, arr)
		}
		cache = append(cache, arrOuter)
	}
	over := int(math.Pow10(9)) + 7
	if m < k || n < k {
		return 0
	}
	return dp(0, 0, k, n, m, over, cache)
}

func dp(i, maxSoFar, remain, n, m, over int, cache [][][]int) int {
	if i == n {
		if remain == 0 {
			return 1
		} else {
			return 0
		}
	}
	if remain < 0 {
		return 0
	}
	if cache[i][maxSoFar][remain] != -1 {
		return cache[i][maxSoFar][remain]
	}
	result := 0
	for j := 1; j <= maxSoFar; j++ {
		result = (result + dp(i+1, maxSoFar, remain, n, m, over, cache)) % over
	}
	for j := maxSoFar + 1; j <= m; j++ {
		result = (result + dp(i+1, j, remain-1, n, m, over, cache)) % over
	}
	cache[i][maxSoFar][remain] = result
	return result
}

func numOfArrays1(n int, m int, k int) int {
	if m < k || n < k {
		return 0
	}
	count := 0
	for i := 1; i <= m; i++ {
		count += dp1(1, n, k-1, m, i)
	}
	return count
}

func dp1(i, maxSoFar, remain, m, last int) int {
	if i == maxSoFar {
		if remain == 0 {
			return 1
		} else {
			return 0
		}
	}
	if remain < 0 {
		return 0
	}
	count := 0
	for j := 1; j <= m; j++ {
		newRemain := remain
		if j > last {
			newRemain--
		}
		count += dp1(i+1, maxSoFar, newRemain, m, j)
	}
	return count
}

// 33 32 31 23 22 21 11 12 13 == 9  2**3
// 222 221 212 211 111 122 121 112 3**2

func isEqualsSearchCost(arr []int, searchCost int) bool {
	maximumValue := -1
	currentCost := 0
	for _, value := range arr {
		if maximumValue < value {
			maximumValue = value
			currentCost += 1
		}
		if currentCost > searchCost {
			return false
		}
	}
	return currentCost == searchCost
}
