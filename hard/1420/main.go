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
