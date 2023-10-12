package main

import "fmt"

func main() {
	fmt.Println(maxDotProduct([]int{2, 1, -2, 5}, []int{3, 0, -6}))
	fmt.Println(maxDotProduct([]int{3, -2}, []int{2, -6, 7}))
	fmt.Println(maxDotProduct([]int{-1, -1}, []int{1, 1}))
	fmt.Println(maxDotProduct([]int{5, -4, -3}, []int{-4, -3, 0, -4, 2}))
}

func maxDotProduct(nums1 []int, nums2 []int) int {
	//optimize if all negative or positive in diferrent arrays
	nums1Max := -1001
	nums1Min := 1001
	nums2Max := -1001
	nums2Min := 1001
	for _, value := range nums1 {
		if value > nums1Max {
			nums1Max = value
		}
		if value < nums1Min {
			nums1Min = value
		}
	}
	for _, value := range nums2 {
		if value > nums2Max {
			nums2Max = value
		}
		if value < nums2Min {
			nums2Min = value
		}
	}
	if nums1Max < 0 && nums2Min > 0 {
		return nums1Max * nums2Min
	}
	if nums1Min > 0 && nums2Max < 0 {
		return nums1Min * nums2Max
	}

	cache := make([][]int, 0)
	for i := 0; i < len(nums1); i++ {
		inner := make([]int, len(nums2), len(nums2))
		cache = append(cache, inner)
	}

	return dp(0, 0, nums1, nums2, cache)
}
func dp(i, j int, nums1, nums2 []int, cache [][]int) int {
	if i == len(nums1) || j == len(nums2) {
		return 0
	}
	if cache[i][j] != 0 {
		return cache[i][j]
	}
	dpij := nums1[i]*nums2[j] + dp(i+1, j+1, nums1, nums2, cache)
	dpi := dp(i+1, j, nums1, nums2, cache)
	dpj := dp(i, j+1, nums1, nums2, cache)
	result := dpij
	if result < dpi {
		result = dpi
	}
	if result < dpj {
		result = dpj
	}
	cache[i][j] = result
	return result
}
