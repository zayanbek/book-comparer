const API_BASE_URL = "http://localhost:8080";

export interface BookSearchResult {
  id: number;
  title: string;
  author: string;
  image: string;
}

export async function searchBooks(
  title: string,
  page: number = 0,
  size: number = 10,
): Promise<BookSearchResult[]> {
  const params = new URLSearchParams({
    title,
    page: page.toString(),
    size: size.toString(),
  });

  const response = await fetch(
    `${API_BASE_URL}/books/search?${params.toString()}`,
  );

  if (!response.ok) {
    throw new Error(
      `Book search failed: ${response.status} ${response.statusText}`,
    );
  }

  return response.json();
}
