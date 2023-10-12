package ru.potemkin.leetcode.hard;

public class Mountain1095 {
    public static void main(String[] args) {
        System.out.println(findInMountainArray(0, new MountainArrayImpl(new int[]{3,5,3,2,0})) == 4);
        System.out.println(findInMountainArray(3, new MountainArrayImpl(new int[]{1,2,3,4,5,6,7,8,1})) == 2);
        System.out.println(findInMountainArray(3, new MountainArrayImpl(new int[]{1,3,2})) == 1);
        System.out.println(findInMountainArray(0, new MountainArrayImpl(new int[]{1,3,2})) == -1);
        System.out.println(findInMountainArray(5, new MountainArrayImpl(new int[]{1,3,2})) == -1);
        System.out.println(findInMountainArray(2, new MountainArrayImpl(new int[]{1,3,4,5,4,3,2})) == 6);
    }
    public static int findInMountainArray(int target, MountainArray mountainArr) {
        int indexPeak = findPeak(mountainArr);
        if (target == mountainArr.get(indexPeak)) {
            return indexPeak;
        }
        int leftIndex = binarySearch(0, indexPeak, mountainArr, target, true);
        if (target == mountainArr.get(leftIndex)) {
            return leftIndex;
        }
        int rightIndex = binarySearch(indexPeak + 1, mountainArr.length(), mountainArr, target, false);
        if (rightIndex != mountainArr.length() && target == mountainArr.get(rightIndex)) {
            return rightIndex;
        }
        return -1;
    }

    private static int binarySearch(int left, int right, MountainArray mountainArray, int target, boolean isLeft) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (target == mountainArray.get(mid)) {
                return mid;
            }
            boolean isLess = mountainArray.get(mid) < target;
            if (isLeft == isLess) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private static int findPeak(MountainArray mountainArr) {
        int left = 0;
        int right = mountainArr.length();
        while (left < right) {
            int mid = (left + right)/2;
            if (mountainArr.get(mid -1) < mountainArr.get(mid) && mountainArr.get(mid) > mountainArr.get(mid + 1)) {
                return mid;
            } else if (mountainArr.get(mid -1) < mountainArr.get(mid) && mountainArr.get(mid) < mountainArr.get(mid + 1)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left + 1;
    }

    interface MountainArray {
        int get(int index);
        int length();
    }

    static class MountainArrayImpl implements MountainArray {
        private final int[] array;

        MountainArrayImpl(int[] array) {
            this.array = array;
        }

        @Override
        public int get(int index) {
            return array[index];
        }

        @Override
        public int length() {
            return array.length;
        }
    }

}
