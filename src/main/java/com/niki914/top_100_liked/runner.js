/**
 * 解析 LeetCode HTML 结构，生成 Markdown 格式的题目列表。
 * @param {string} htmlString 包含题目列表的 HTML 字符串。
 * @returns {string} 格式化后的 Markdown 字符串。
 */
function parseLeetCodeHtml(htmlString) {
    // 1. 创建一个临时的 DOM 元素容器
    const parser = new DOMParser();
    const doc = parser.parseFromString(htmlString, 'text/html');

    // 假设您的 HTML 结构是整个文档的一部分
    const container = doc.querySelector('.relative.flex.flex-col > .flex.w-full.flex-col.gap-4');

    if (!container) {
        return "错误：未找到主要的题目容器。";
    }

    let markdownOutput = "";

    // 2. 遍历所有的题目分类块 (w-full overflow-hidden...)
    const categoryBlocks = container.querySelectorAll('.w-full.overflow-hidden');

    categoryBlocks.forEach(block => {
        // 提取类别名称，例如 "哈希"
        const categoryNameElement = block.querySelector('.flex.h-10.items-center div');
        const categoryName = categoryNameElement ? categoryNameElement.textContent.trim() : "未知类别";

        // 添加一级 Markdown 标题
        markdownOutput += `## ${categoryName}\n`;

        // 3. 遍历该类别下的所有题目 (flex.flex-col.border-b...)
        const problemItems = block.querySelectorAll('.flex.flex-col.border-b');

        problemItems.forEach(item => {
            // 提取题目名称
            const titleElement = item.querySelector('.text-body.max-w-\\[75\\%\\].font-medium .truncate');
            const title = titleElement ? titleElement.textContent.trim() : "未知题目";

            // 提取难度
            const difficultyElement = item.querySelector('.relative.flex.h-full.w-full.items-center > p');
            const difficulty = difficultyElement ? difficultyElement.textContent.trim() : "未知难度";

            // 格式化为 Markdown 列表项
            markdownOutput += `- ${title} - ${difficulty}\n`;
        });

        // 保持格式清晰，添加空行
        markdownOutput += "\n";
    });

    return markdownOutput.trim();
}

// 示例输入：将您的 HTML 结构放入字符串中
const htmlString = `
<div class="relative flex flex-col">
    <div class="flex w-full flex-col gap-4">
        <div class="w-full overflow-hidden rounded-lg border-[1.5px] border-lc-fill-02 dark:border-dark-lc-fill-02">
            <div class="flex h-10 items-center justify-between px-4 font-medium text-lc-text-primary dark:text-dark-lc-text-primary bg-lc-fill-01 dark:bg-dark-lc-fill-01">
                <div class="text-[12px] font-medium">哈希</div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">1. 两数之和</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-green-60 dark:text-dark-lc-green-60">简单</p>
                    </div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">49. 字母异位词分组</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">128. 最长连续序列</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
        </div>
        <div class="w-full overflow-hidden rounded-lg border-[1.5px] border-lc-fill-02 dark:border-dark-lc-fill-02">
            <div class="flex h-10 items-center justify-between px-4 font-medium text-lc-text-primary dark:text-dark-lc-text-primary bg-lc-fill-01 dark:bg-dark-lc-fill-01">
                <div class="text-[12px] font-medium">双指针</div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">283. 移动零</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-green-60 dark:text-dark-lc-green-60">简单</p>
                    </div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">11. 盛最多水的容器</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">15. 三数之和</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">42. 接雨水</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-red-60 dark:text-dark-lc-red-60">困难</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="w-full overflow-hidden rounded-lg border-[1.5px] border-lc-fill-02 dark:border-dark-lc-fill-02">
            <div class="flex h-10 items-center justify-between px-4 font-medium text-lc-text-primary dark:text-dark-lc-text-primary bg-lc-fill-01 dark:bg-dark-lc-fill-01">
                <div class="text-[12px] font-medium">滑动窗口</div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">3. 无重复字符的最长子串</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">438. 找到字符串中所有字母异位词</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
        </div>
        <div class="w-full overflow-hidden rounded-lg border-[1.5px] border-lc-fill-02 dark:border-dark-lc-fill-02">
            <div class="flex h-10 items-center justify-between px-4 font-medium text-lc-text-primary dark:text-dark-lc-text-primary bg-lc-fill-01 dark:bg-dark-lc-fill-01">
                <div class="text-[12px] font-medium">子串</div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">560. 和为 K 的子数组</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">239. 滑动窗口最大值</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-red-60 dark:text-dark-lc-red-60">困难</p>
                    </div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed"></div>
                        <div>
                            <div class="!ml-0 cursor-pointer text-[0px]" type="button"
                                 aria-haspopup="dialog" aria-expanded="false"
                                 aria-controls="radix-:r27:" data-state="closed">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                     width="1em" height="1em" fill="currentColor"
                                     class="h-4.5 w-4.5 inline-block shrink-0 fill-none stroke-current text-lc-icon-secondary dark:text-dark-lc-icon-secondary hover:text-lc-icon-primary dark:hover:text-dark-lc-icon-primary">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          stroke-width="2"
                                          d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">76. 最小覆盖子串</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-red-60 dark:text-dark-lc-red-60">困难</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="w-full overflow-hidden rounded-lg border-[1.5px] border-lc-fill-02 dark:border-dark-lc-fill-02">
            <div class="flex h-10 items-center justify-between px-4 font-medium text-lc-text-primary dark:text-dark-lc-text-primary bg-lc-fill-01 dark:bg-dark-lc-fill-01">
                <div class="text-[12px] font-medium">普通数组</div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">53. 最大子数组和</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">56. 合并区间</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">189. 轮转数组</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">238. 除自身以外数组的乘积</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">41. 缺失的第一个正数</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-red-60 dark:text-dark-lc-red-60">困难</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="w-full overflow-hidden rounded-lg border-[1.5px] border-lc-fill-02 dark:border-dark-lc-fill-02">
            <div class="flex h-10 items-center justify-between px-4 font-medium text-lc-text-primary dark:text-dark-lc-text-primary bg-lc-fill-01 dark:bg-dark-lc-fill-01">
                <div class="text-[12px] font-medium">矩阵</div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">73. 矩阵置零</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">54. 螺旋矩阵</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">48. 旋转图像</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">240. 搜索二维矩阵 II</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
        </div>
        <div class="w-full overflow-hidden rounded-lg border-[1.5px] border-lc-fill-02 dark:border-dark-lc-fill-02">
            <div class="flex h-10 items-center justify-between px-4 font-medium text-lc-text-primary dark:text-dark-lc-text-primary bg-lc-fill-01 dark:bg-dark-lc-fill-01">
                <div class="text-[12px] font-medium">链表</div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">160. 相交链表</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-green-60 dark:text-dark-lc-green-60">简单</p>
                    </div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">206. 反转链表</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-green-60 dark:text-dark-lc-green-60">简单</p>
                    </div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">234. 回文链表</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-green-60 dark:text-dark-lc-green-60">简单</p>
                    </div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">141. 环形链表</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-green-60 dark:text-dark-lc-green-60">简单</p>
                    </div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">142. 环形链表 II</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">21. 合并两个有序链表</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-green-60 dark:text-dark-lc-green-60">简单</p>
                    </div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">2. 两数相加</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">19. 删除链表的倒数第 N 个结点</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">24. 两两交换链表中的节点</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed"></div>
                        <div>
                            <div class="!ml-0 cursor-pointer text-[0px]" type="button"
                                 aria-haspopup="dialog" aria-expanded="false"
                                 aria-controls="radix-:r2r:" data-state="closed">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                     width="1em" height="1em" fill="currentColor"
                                     class="h-4.5 w-4.5 inline-block shrink-0 fill-none stroke-current text-lc-icon-secondary dark:text-dark-lc-icon-secondary hover:text-lc-icon-primary dark:hover:text-dark-lc-icon-primary">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          stroke-width="2"
                                          d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">25. K 个一组翻转链表</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-red-60 dark:text-dark-lc-red-60">困难</p>
                    </div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">138. 随机链表的复制</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">148. 排序链表</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed"></div>
                        <div>
                            <div class="!ml-0 cursor-pointer text-[0px]" type="button"
                                 aria-haspopup="dialog" aria-expanded="false"
                                 aria-controls="radix-:r2v:" data-state="closed">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                     width="1em" height="1em" fill="currentColor"
                                     class="h-4.5 w-4.5 inline-block shrink-0 fill-none stroke-current text-lc-icon-secondary dark:text-dark-lc-icon-secondary hover:text-lc-icon-primary dark:hover:text-dark-lc-icon-primary">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          stroke-width="2"
                                          d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">23. 合并 K 个升序链表</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-red-60 dark:text-dark-lc-red-60">困难</p>
                    </div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">146. LRU 缓存</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
        </div>
        <div class="w-full overflow-hidden rounded-lg border-[1.5px] border-lc-fill-02 dark:border-dark-lc-fill-02">
            <div class="flex h-10 items-center justify-between px-4 font-medium text-lc-text-primary dark:text-dark-lc-text-primary bg-lc-fill-01 dark:bg-dark-lc-fill-01">
                <div class="text-[12px] font-medium">二叉树</div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">94. 二叉树的中序遍历</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-green-60 dark:text-dark-lc-green-60">简单</p>
                    </div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">104. 二叉树的最大深度</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-green-60 dark:text-dark-lc-green-60">简单</p>
                    </div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">226. 翻转二叉树</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-green-60 dark:text-dark-lc-green-60">简单</p>
                    </div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">101. 对称二叉树</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-green-60 dark:text-dark-lc-green-60">简单</p>
                    </div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">543. 二叉树的直径</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-green-60 dark:text-dark-lc-green-60">简单</p>
                    </div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">102. 二叉树的层序遍历</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">108. 将有序数组转换为二叉搜索树</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-green-60 dark:text-dark-lc-green-60">简单</p>
                    </div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">98. 验证二叉搜索树</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">230. 二叉搜索树中第 K 小的元素</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">199. 二叉树的右视图</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">114. 二叉树展开为链表</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 cursor-pointer bg-lc-fill-02 dark:bg-dark-lc-fill-02"
                 id="activeItem">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="w-4.5 h-4.5 inline-block shrink-0 text-lc-icon-tertiary dark:text-dark-lc-icon-tertiary">
                                <path fill-rule="evenodd"
                                      d="M12 22C6.477 22 2 17.523 2 12S6.477 2 12 2s10 4.477 10 10-4.477 10-10 10zm0-2a8 8 0 100-16 8 8 0 000 16z"
                                      clip-rule="evenodd"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-system-primary dark:text-dark-lc-system-primary">
                                <div class="truncate">105. 从前序与中序遍历序列构造二叉树</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed"></div>
                        <div>
                            <div class="!ml-0 cursor-pointer text-[0px]" type="button"
                                 aria-haspopup="dialog" aria-expanded="false"
                                 aria-controls="radix-:r3h:" data-state="closed">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                     width="1em" height="1em" fill="currentColor"
                                     class="h-4.5 w-4.5 inline-block shrink-0 fill-none stroke-current text-lc-icon-secondary dark:text-dark-lc-icon-secondary hover:text-lc-icon-primary dark:hover:text-dark-lc-icon-primary">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          stroke-width="2"
                                          d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">437. 路径总和 III</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed"></div>
                        <div>
                            <div class="!ml-0 cursor-pointer text-[0px]" type="button"
                                 aria-haspopup="dialog" aria-expanded="false"
                                 aria-controls="radix-:r3j:" data-state="closed">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                     width="1em" height="1em" fill="currentColor"
                                     class="h-4.5 w-4.5 inline-block shrink-0 fill-none stroke-current text-lc-icon-secondary dark:text-dark-lc-icon-secondary hover:text-lc-icon-primary dark:hover:text-dark-lc-icon-primary">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          stroke-width="2"
                                          d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">236. 二叉树的最近公共祖先</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed"></div>
                        <div>
                            <div class="!ml-0 cursor-pointer text-[0px]" type="button"
                                 aria-haspopup="dialog" aria-expanded="false"
                                 aria-controls="radix-:r3l:" data-state="closed">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                     width="1em" height="1em" fill="currentColor"
                                     class="h-4.5 w-4.5 inline-block shrink-0 fill-none stroke-current text-lc-icon-secondary dark:text-dark-lc-icon-secondary hover:text-lc-icon-primary dark:hover:text-dark-lc-icon-primary">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          stroke-width="2"
                                          d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">124. 二叉树中的最大路径和</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-red-60 dark:text-dark-lc-red-60">困难</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="w-full overflow-hidden rounded-lg border-[1.5px] border-lc-fill-02 dark:border-dark-lc-fill-02">
            <div class="flex h-10 items-center justify-between px-4 font-medium text-lc-text-primary dark:text-dark-lc-text-primary bg-lc-fill-01 dark:bg-dark-lc-fill-01">
                <div class="text-[12px] font-medium">图论</div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed"></div>
                        <div>
                            <div class="!ml-0 cursor-pointer text-[0px]" type="button"
                                 aria-haspopup="dialog" aria-expanded="false"
                                 aria-controls="radix-:r3n:" data-state="closed">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                     width="1em" height="1em" fill="currentColor"
                                     class="h-4.5 w-4.5 inline-block shrink-0 fill-none stroke-current text-lc-icon-secondary dark:text-dark-lc-icon-secondary hover:text-lc-icon-primary dark:hover:text-dark-lc-icon-primary">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          stroke-width="2"
                                          d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">200. 岛屿数量</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed"></div>
                        <div>
                            <div class="!ml-0 cursor-pointer text-[0px]" type="button"
                                 aria-haspopup="dialog" aria-expanded="false"
                                 aria-controls="radix-:r3p:" data-state="closed">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                     width="1em" height="1em" fill="currentColor"
                                     class="h-4.5 w-4.5 inline-block shrink-0 fill-none stroke-current text-lc-icon-secondary dark:text-dark-lc-icon-secondary hover:text-lc-icon-primary dark:hover:text-dark-lc-icon-primary">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          stroke-width="2"
                                          d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">994. 腐烂的橘子</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed"></div>
                        <div>
                            <div class="!ml-0 cursor-pointer text-[0px]" type="button"
                                 aria-haspopup="dialog" aria-expanded="false"
                                 aria-controls="radix-:r3r:" data-state="closed">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                     width="1em" height="1em" fill="currentColor"
                                     class="h-4.5 w-4.5 inline-block shrink-0 fill-none stroke-current text-lc-icon-secondary dark:text-dark-lc-icon-secondary hover:text-lc-icon-primary dark:hover:text-dark-lc-icon-primary">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          stroke-width="2"
                                          d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">207. 课程表</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed"></div>
                        <div>
                            <div class="!ml-0 cursor-pointer text-[0px]" type="button"
                                 aria-haspopup="dialog" aria-expanded="false"
                                 aria-controls="radix-:r3t:" data-state="closed">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                     width="1em" height="1em" fill="currentColor"
                                     class="h-4.5 w-4.5 inline-block shrink-0 fill-none stroke-current text-lc-icon-secondary dark:text-dark-lc-icon-secondary hover:text-lc-icon-primary dark:hover:text-dark-lc-icon-primary">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          stroke-width="2"
                                          d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">208. 实现 Trie (前缀树)</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
        </div>
        <div class="w-full overflow-hidden rounded-lg border-[1.5px] border-lc-fill-02 dark:border-dark-lc-fill-02">
            <div class="flex h-10 items-center justify-between px-4 font-medium text-lc-text-primary dark:text-dark-lc-text-primary bg-lc-fill-01 dark:bg-dark-lc-fill-01">
                <div class="text-[12px] font-medium">回溯</div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed"></div>
                        <div>
                            <div class="!ml-0 cursor-pointer text-[0px]" type="button"
                                 aria-haspopup="dialog" aria-expanded="false"
                                 aria-controls="radix-:r3v:" data-state="closed">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                     width="1em" height="1em" fill="currentColor"
                                     class="h-4.5 w-4.5 inline-block shrink-0 fill-none stroke-current text-lc-icon-secondary dark:text-dark-lc-icon-secondary hover:text-lc-icon-primary dark:hover:text-dark-lc-icon-primary">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          stroke-width="2"
                                          d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">46. 全排列</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed"></div>
                        <div>
                            <div class="!ml-0 cursor-pointer text-[0px]" type="button"
                                 aria-haspopup="dialog" aria-expanded="false"
                                 aria-controls="radix-:r41:" data-state="closed">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                     width="1em" height="1em" fill="currentColor"
                                     class="h-4.5 w-4.5 inline-block shrink-0 fill-none stroke-current text-lc-icon-secondary dark:text-dark-lc-icon-secondary hover:text-lc-icon-primary dark:hover:text-dark-lc-icon-primary">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          stroke-width="2"
                                          d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">78. 子集</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed"></div>
                        <div>
                            <div class="!ml-0 cursor-pointer text-[0px]" type="button"
                                 aria-haspopup="dialog" aria-expanded="false"
                                 aria-controls="radix-:r43:" data-state="closed">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                     width="1em" height="1em" fill="currentColor"
                                     class="h-4.5 w-4.5 inline-block shrink-0 fill-none stroke-current text-lc-icon-secondary dark:text-dark-lc-icon-secondary hover:text-lc-icon-primary dark:hover:text-dark-lc-icon-primary">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          stroke-width="2"
                                          d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">17. 电话号码的字母组合</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed"></div>
                        <div>
                            <div class="!ml-0 cursor-pointer text-[0px]" type="button"
                                 aria-haspopup="dialog" aria-expanded="false"
                                 aria-controls="radix-:r45:" data-state="closed">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                     width="1em" height="1em" fill="currentColor"
                                     class="h-4.5 w-4.5 inline-block shrink-0 fill-none stroke-current text-lc-icon-secondary dark:text-dark-lc-icon-secondary hover:text-lc-icon-primary dark:hover:text-dark-lc-icon-primary">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          stroke-width="2"
                                          d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">39. 组合总和</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed"></div>
                        <div>
                            <div class="!ml-0 cursor-pointer text-[0px]" type="button"
                                 aria-haspopup="dialog" aria-expanded="false"
                                 aria-controls="radix-:r47:" data-state="closed">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                     width="1em" height="1em" fill="currentColor"
                                     class="h-4.5 w-4.5 inline-block shrink-0 fill-none stroke-current text-lc-icon-secondary dark:text-dark-lc-icon-secondary hover:text-lc-icon-primary dark:hover:text-dark-lc-icon-primary">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          stroke-width="2"
                                          d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">22. 括号生成</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed"></div>
                        <div>
                            <div class="!ml-0 cursor-pointer text-[0px]" type="button"
                                 aria-haspopup="dialog" aria-expanded="false"
                                 aria-controls="radix-:r49:" data-state="closed">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                     width="1em" height="1em" fill="currentColor"
                                     class="h-4.5 w-4.5 inline-block shrink-0 fill-none stroke-current text-lc-icon-secondary dark:text-dark-lc-icon-secondary hover:text-lc-icon-primary dark:hover:text-dark-lc-icon-primary">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          stroke-width="2"
                                          d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">79. 单词搜索</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed"></div>
                        <div>
                            <div class="!ml-0 cursor-pointer text-[0px]" type="button"
                                 aria-haspopup="dialog" aria-expanded="false"
                                 aria-controls="radix-:r4b:" data-state="closed">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                     width="1em" height="1em" fill="currentColor"
                                     class="h-4.5 w-4.5 inline-block shrink-0 fill-none stroke-current text-lc-icon-secondary dark:text-dark-lc-icon-secondary hover:text-lc-icon-primary dark:hover:text-dark-lc-icon-primary">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          stroke-width="2"
                                          d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">131. 分割回文串</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed"></div>
                        <div>
                            <div class="!ml-0 cursor-pointer text-[0px]" type="button"
                                 aria-haspopup="dialog" aria-expanded="false"
                                 aria-controls="radix-:r4d:" data-state="closed">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                     width="1em" height="1em" fill="currentColor"
                                     class="h-4.5 w-4.5 inline-block shrink-0 fill-none stroke-current text-lc-icon-secondary dark:text-dark-lc-icon-secondary hover:text-lc-icon-primary dark:hover:text-dark-lc-icon-primary">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          stroke-width="2"
                                          d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">51. N 皇后</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-red-60 dark:text-dark-lc-red-60">困难</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="w-full overflow-hidden rounded-lg border-[1.5px] border-lc-fill-02 dark:border-dark-lc-fill-02">
            <div class="flex h-10 items-center justify-between px-4 font-medium text-lc-text-primary dark:text-dark-lc-text-primary bg-lc-fill-01 dark:bg-dark-lc-fill-01">
                <div class="text-[12px] font-medium">二分查找</div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed"></div>
                        <div>
                            <div class="!ml-0 cursor-pointer text-[0px]" type="button"
                                 aria-haspopup="dialog" aria-expanded="false"
                                 aria-controls="radix-:r4f:" data-state="closed">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                     width="1em" height="1em" fill="currentColor"
                                     class="h-4.5 w-4.5 inline-block shrink-0 fill-none stroke-current text-lc-icon-secondary dark:text-dark-lc-icon-secondary hover:text-lc-icon-primary dark:hover:text-dark-lc-icon-primary">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          stroke-width="2"
                                          d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">35. 搜索插入位置</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-green-60 dark:text-dark-lc-green-60">简单</p>
                    </div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed"></div>
                        <div>
                            <div class="!ml-0 cursor-pointer text-[0px]" type="button"
                                 aria-haspopup="dialog" aria-expanded="false"
                                 aria-controls="radix-:r4h:" data-state="closed">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                     width="1em" height="1em" fill="currentColor"
                                     class="h-4.5 w-4.5 inline-block shrink-0 fill-none stroke-current text-lc-icon-secondary dark:text-dark-lc-icon-secondary hover:text-lc-icon-primary dark:hover:text-dark-lc-icon-primary">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          stroke-width="2"
                                          d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">74. 搜索二维矩阵</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed"></div>
                        <div>
                            <div class="!ml-0 cursor-pointer text-[0px]" type="button"
                                 aria-haspopup="dialog" aria-expanded="false"
                                 aria-controls="radix-:r4j:" data-state="closed">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                     width="1em" height="1em" fill="currentColor"
                                     class="h-4.5 w-4.5 inline-block shrink-0 fill-none stroke-current text-lc-icon-secondary dark:text-dark-lc-icon-secondary hover:text-lc-icon-primary dark:hover:text-dark-lc-icon-primary">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          stroke-width="2"
                                          d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">34.
                                    在排序数组中查找元素的第一个和最后一个位置
                                </div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed"></div>
                        <div>
                            <div class="!ml-0 cursor-pointer text-[0px]" type="button"
                                 aria-haspopup="dialog" aria-expanded="false"
                                 aria-controls="radix-:r4l:" data-state="closed">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                     width="1em" height="1em" fill="currentColor"
                                     class="h-4.5 w-4.5 inline-block shrink-0 fill-none stroke-current text-lc-icon-secondary dark:text-dark-lc-icon-secondary hover:text-lc-icon-primary dark:hover:text-dark-lc-icon-primary">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          stroke-width="2"
                                          d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">33. 搜索旋转排序数组</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed"></div>
                        <div>
                            <div class="!ml-0 cursor-pointer text-[0px]" type="button"
                                 aria-haspopup="dialog" aria-expanded="false"
                                 aria-controls="radix-:r4n:" data-state="closed">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                     width="1em" height="1em" fill="currentColor"
                                     class="h-4.5 w-4.5 inline-block shrink-0 fill-none stroke-current text-lc-icon-secondary dark:text-dark-lc-icon-secondary hover:text-lc-icon-primary dark:hover:text-dark-lc-icon-primary">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          stroke-width="2"
                                          d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">153. 寻找旋转排序数组中的最小值</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="w-4.5 h-4.5 inline-block shrink-0 text-lc-icon-tertiary dark:text-dark-lc-icon-tertiary">
                                <path fill-rule="evenodd"
                                      d="M12 22C6.477 22 2 17.523 2 12S6.477 2 12 2s10 4.477 10 10-4.477 10-10 10zm0-2a8 8 0 100-16 8 8 0 000 16z"
                                      clip-rule="evenodd"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">4. 寻找两个正序数组的中位数</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-red-60 dark:text-dark-lc-red-60">困难</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="w-full overflow-hidden rounded-lg border-[1.5px] border-lc-fill-02 dark:border-dark-lc-fill-02">
            <div class="flex h-10 items-center justify-between px-4 font-medium text-lc-text-primary dark:text-dark-lc-text-primary bg-lc-fill-01 dark:bg-dark-lc-fill-01">
                <div class="text-[12px] font-medium">栈</div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed"></div>
                        <div>
                            <div class="!ml-0 cursor-pointer text-[0px]" type="button"
                                 aria-haspopup="dialog" aria-expanded="false"
                                 aria-controls="radix-:r4q:" data-state="closed">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                     width="1em" height="1em" fill="currentColor"
                                     class="h-4.5 w-4.5 inline-block shrink-0 fill-none stroke-current text-lc-icon-secondary dark:text-dark-lc-icon-secondary hover:text-lc-icon-primary dark:hover:text-dark-lc-icon-primary">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          stroke-width="2"
                                          d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">20. 有效的括号</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-green-60 dark:text-dark-lc-green-60">简单</p>
                    </div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed"></div>
                        <div>
                            <div class="!ml-0 cursor-pointer text-[0px]" type="button"
                                 aria-haspopup="dialog" aria-expanded="false"
                                 aria-controls="radix-:r4s:" data-state="closed">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                     width="1em" height="1em" fill="currentColor"
                                     class="h-4.5 w-4.5 inline-block shrink-0 fill-none stroke-current text-lc-icon-secondary dark:text-dark-lc-icon-secondary hover:text-lc-icon-primary dark:hover:text-dark-lc-icon-primary">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          stroke-width="2"
                                          d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">155. 最小栈</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed"></div>
                        <div>
                            <div class="!ml-0 cursor-pointer text-[0px]" type="button"
                                 aria-haspopup="dialog" aria-expanded="false"
                                 aria-controls="radix-:r4u:" data-state="closed">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                     width="1em" height="1em" fill="currentColor"
                                     class="h-4.5 w-4.5 inline-block shrink-0 fill-none stroke-current text-lc-icon-secondary dark:text-dark-lc-icon-secondary hover:text-lc-icon-primary dark:hover:text-dark-lc-icon-primary">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          stroke-width="2"
                                          d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">394. 字符串解码</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed"></div>
                        <div>
                            <div class="!ml-0 cursor-pointer text-[0px]" type="button"
                                 aria-haspopup="dialog" aria-expanded="false"
                                 aria-controls="radix-:r50:" data-state="closed">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                     width="1em" height="1em" fill="currentColor"
                                     class="h-4.5 w-4.5 inline-block shrink-0 fill-none stroke-current text-lc-icon-secondary dark:text-dark-lc-icon-secondary hover:text-lc-icon-primary dark:hover:text-dark-lc-icon-primary">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          stroke-width="2"
                                          d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">739. 每日温度</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="w-4.5 h-4.5 inline-block shrink-0 text-lc-icon-tertiary dark:text-dark-lc-icon-tertiary">
                                <path fill-rule="evenodd"
                                      d="M12 22C6.477 22 2 17.523 2 12S6.477 2 12 2s10 4.477 10 10-4.477 10-10 10zm0-2a8 8 0 100-16 8 8 0 000 16z"
                                      clip-rule="evenodd"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">84. 柱状图中最大的矩形</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-red-60 dark:text-dark-lc-red-60">困难</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="w-full overflow-hidden rounded-lg border-[1.5px] border-lc-fill-02 dark:border-dark-lc-fill-02">
            <div class="flex h-10 items-center justify-between px-4 font-medium text-lc-text-primary dark:text-dark-lc-text-primary bg-lc-fill-01 dark:bg-dark-lc-fill-01">
                <div class="text-[12px] font-medium">堆</div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed"></div>
                        <div>
                            <div class="!ml-0 cursor-pointer text-[0px]" type="button"
                                 aria-haspopup="dialog" aria-expanded="false"
                                 aria-controls="radix-:r53:" data-state="closed">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                     width="1em" height="1em" fill="currentColor"
                                     class="h-4.5 w-4.5 inline-block shrink-0 fill-none stroke-current text-lc-icon-secondary dark:text-dark-lc-icon-secondary hover:text-lc-icon-primary dark:hover:text-dark-lc-icon-primary">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          stroke-width="2"
                                          d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">215. 数组中的第K个最大元素</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed"></div>
                        <div>
                            <div class="!ml-0 cursor-pointer text-[0px]" type="button"
                                 aria-haspopup="dialog" aria-expanded="false"
                                 aria-controls="radix-:r55:" data-state="closed">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                     width="1em" height="1em" fill="currentColor"
                                     class="h-4.5 w-4.5 inline-block shrink-0 fill-none stroke-current text-lc-icon-secondary dark:text-dark-lc-icon-secondary hover:text-lc-icon-primary dark:hover:text-dark-lc-icon-primary">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          stroke-width="2"
                                          d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">347. 前 K 个高频元素</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed"></div>
                        <div>
                            <div class="!ml-0 cursor-pointer text-[0px]" type="button"
                                 aria-haspopup="dialog" aria-expanded="false"
                                 aria-controls="radix-:r57:" data-state="closed">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                     width="1em" height="1em" fill="currentColor"
                                     class="h-4.5 w-4.5 inline-block shrink-0 fill-none stroke-current text-lc-icon-secondary dark:text-dark-lc-icon-secondary hover:text-lc-icon-primary dark:hover:text-dark-lc-icon-primary">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          stroke-width="2"
                                          d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">295. 数据流的中位数</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-red-60 dark:text-dark-lc-red-60">困难</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="w-full overflow-hidden rounded-lg border-[1.5px] border-lc-fill-02 dark:border-dark-lc-fill-02">
            <div class="flex h-10 items-center justify-between px-4 font-medium text-lc-text-primary dark:text-dark-lc-text-primary bg-lc-fill-01 dark:bg-dark-lc-fill-01">
                <div class="text-[12px] font-medium">贪心算法</div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed"></div>
                        <div>
                            <div class="!ml-0 cursor-pointer text-[0px]" type="button"
                                 aria-haspopup="dialog" aria-expanded="false"
                                 aria-controls="radix-:r59:" data-state="closed">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                     width="1em" height="1em" fill="currentColor"
                                     class="h-4.5 w-4.5 inline-block shrink-0 fill-none stroke-current text-lc-icon-secondary dark:text-dark-lc-icon-secondary hover:text-lc-icon-primary dark:hover:text-dark-lc-icon-primary">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          stroke-width="2"
                                          d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">121. 买卖股票的最佳时机</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-green-60 dark:text-dark-lc-green-60">简单</p>
                    </div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed"></div>
                        <div>
                            <div class="!ml-0 cursor-pointer text-[0px]" type="button"
                                 aria-haspopup="dialog" aria-expanded="false"
                                 aria-controls="radix-:r5b:" data-state="closed">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                     width="1em" height="1em" fill="currentColor"
                                     class="h-4.5 w-4.5 inline-block shrink-0 fill-none stroke-current text-lc-icon-secondary dark:text-dark-lc-icon-secondary hover:text-lc-icon-primary dark:hover:text-dark-lc-icon-primary">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          stroke-width="2"
                                          d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">55. 跳跃游戏</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed"></div>
                        <div>
                            <div class="!ml-0 cursor-pointer text-[0px]" type="button"
                                 aria-haspopup="dialog" aria-expanded="false"
                                 aria-controls="radix-:r5d:" data-state="closed">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                     width="1em" height="1em" fill="currentColor"
                                     class="h-4.5 w-4.5 inline-block shrink-0 fill-none stroke-current text-lc-icon-secondary dark:text-dark-lc-icon-secondary hover:text-lc-icon-primary dark:hover:text-dark-lc-icon-primary">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          stroke-width="2"
                                          d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">45. 跳跃游戏 II</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed"></div>
                        <div>
                            <div class="!ml-0 cursor-pointer text-[0px]" type="button"
                                 aria-haspopup="dialog" aria-expanded="false"
                                 aria-controls="radix-:r5f:" data-state="closed">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                     width="1em" height="1em" fill="currentColor"
                                     class="h-4.5 w-4.5 inline-block shrink-0 fill-none stroke-current text-lc-icon-secondary dark:text-dark-lc-icon-secondary hover:text-lc-icon-primary dark:hover:text-dark-lc-icon-primary">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          stroke-width="2"
                                          d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">763. 划分字母区间</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
        </div>
        <div class="w-full overflow-hidden rounded-lg border-[1.5px] border-lc-fill-02 dark:border-dark-lc-fill-02">
            <div class="flex h-10 items-center justify-between px-4 font-medium text-lc-text-primary dark:text-dark-lc-text-primary bg-lc-fill-01 dark:bg-dark-lc-fill-01">
                <div class="text-[12px] font-medium">动态规划</div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed"></div>
                        <div>
                            <div class="!ml-0 cursor-pointer text-[0px]" type="button"
                                 aria-haspopup="dialog" aria-expanded="false"
                                 aria-controls="radix-:r5h:" data-state="closed">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                     width="1em" height="1em" fill="currentColor"
                                     class="h-4.5 w-4.5 inline-block shrink-0 fill-none stroke-current text-lc-icon-secondary dark:text-dark-lc-icon-secondary hover:text-lc-icon-primary dark:hover:text-dark-lc-icon-primary">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          stroke-width="2"
                                          d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">70. 爬楼梯</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-green-60 dark:text-dark-lc-green-60">简单</p>
                    </div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">118. 杨辉三角</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-green-60 dark:text-dark-lc-green-60">简单</p>
                    </div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">198. 打家劫舍</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">279. 完全平方数</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">322. 零钱兑换</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">139. 单词拆分</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">300. 最长递增子序列</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">152. 乘积最大子数组</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="w-4.5 h-4.5 inline-block shrink-0 text-lc-icon-tertiary dark:text-dark-lc-icon-tertiary">
                                <path fill-rule="evenodd"
                                      d="M12 22C6.477 22 2 17.523 2 12S6.477 2 12 2s10 4.477 10 10-4.477 10-10 10zm0-2a8 8 0 100-16 8 8 0 000 16z"
                                      clip-rule="evenodd"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">416. 分割等和子集</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="w-4.5 h-4.5 inline-block shrink-0 text-lc-icon-tertiary dark:text-dark-lc-icon-tertiary">
                                <path fill-rule="evenodd"
                                      d="M12 22C6.477 22 2 17.523 2 12S6.477 2 12 2s10 4.477 10 10-4.477 10-10 10zm0-2a8 8 0 100-16 8 8 0 000 16z"
                                      clip-rule="evenodd"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">32. 最长有效括号</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-red-60 dark:text-dark-lc-red-60">困难</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="w-full overflow-hidden rounded-lg border-[1.5px] border-lc-fill-02 dark:border-dark-lc-fill-02">
            <div class="flex h-10 items-center justify-between px-4 font-medium text-lc-text-primary dark:text-dark-lc-text-primary bg-lc-fill-01 dark:bg-dark-lc-fill-01">
                <div class="text-[12px] font-medium">多维动态规划</div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">62. 不同路径</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">64. 最小路径和</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">5. 最长回文子串</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">1143. 最长公共子序列</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">72. 编辑距离</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
        </div>
        <div class="w-full overflow-hidden rounded-lg border-[1.5px] border-lc-fill-02 dark:border-dark-lc-fill-02">
            <div class="flex h-10 items-center justify-between px-4 font-medium text-lc-text-primary dark:text-dark-lc-text-primary bg-lc-fill-01 dark:bg-dark-lc-fill-01">
                <div class="text-[12px] font-medium">技巧</div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">136. 只出现一次的数字</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-green-60 dark:text-dark-lc-green-60">简单</p>
                    </div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">169. 多数元素</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-green-60 dark:text-dark-lc-green-60">简单</p>
                    </div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">75. 颜色分类</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-green-60 dark:text-dark-lc-green-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 110-19.2c1.507 0 2.932.347 4.2.965M19.8 6l-8.4 8.4L9 12"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">31. 下一个排列</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
            <div class="flex flex-col border-b-[1.5px] duration-300 last:border-b-0 border-lc-fill-02 dark:border-dark-lc-fill-02 hover:bg-lc-fill-02 dark:hover:bg-dark-lc-fill-02 cursor-pointer"
                 id="">
                <div class="flex h-[52px] w-full items-center space-x-3 px-4">
                    <div>
                        <div class="flex items-center" data-state="closed">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em"
                                 height="1em" fill="currentColor"
                                 class="h-4.5 w-4.5 text-lc-yellow-60 dark:text-dark-lc-yellow-60 inline-block shrink-0 fill-none stroke-current">
                                <path stroke-width="2"
                                      d="M18 12a6.002 6.002 0 01-5.004 5.918c-.545.09-.996-.366-.996-.918V7c0-.552.451-1.009.996-.918A6.002 6.002 0 0118 12z"></path>
                                <path stroke-width="2"
                                      d="M21.6 12a9.6 9.6 0 01-9.6 9.6 9.6 9.6 0 119.6-9.6z"
                                      clip-rule="evenodd"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="relative flex h-full w-full items-center">
                        <div class=" flex w-0 flex-1 items-center space-x-2">
                            <div class="text-body max-w-[75%] font-medium text-lc-text-primary dark:text-dark-lc-text-primary">
                                <div class="truncate">287. 寻找重复数</div>
                            </div>
                        </div>
                        <p class="text-[14px] text-lc-yellow-60 dark:text-dark-lc-yellow-60">
                            中等</p></div>
                </div>
            </div>
        </div>
    </div>
</div>
`