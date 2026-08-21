import { API_BASE_URL } from "./config";

export interface ComparisonResult {
  bookA: string;
  bookB: string;
  cosineSimilarity: number;
  klDivergenceAB: number;
  klDivergenceBA: number;
  jsDivergence: number;
}

export async function compareBooks(
  bookAId: number,
  bookBId: number,
): Promise<ComparisonResult> {
  const response = await fetch(
    `${API_BASE_URL}/comparisons?bookAId=${bookAId}&bookBId=${bookBId}`,
  );

  if (!response.ok) {
    throw new Error(`Failed to compare books: ${response.status}`);
  }

  const data = await response.json();

  return {
    ...data,
    idA: Number(data.idA),
    idB: Number(data.idB),
    cosineSimilarity: Number(data.cosineSimilarity),
    klDivergenceAB: Number(data.klDivergenceAB),
    klDivergenceBA: Number(data.klDivergenceBA),
    jsDivergence: Number(data.jsDivergence),
  };
}
