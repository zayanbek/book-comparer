import { useEffect, useState } from "react";
import { compareBooks, type ComparisonResult } from "../../api/comparisons";
import { type BookSearchResult } from "../../api/books";
import "./Metrics.css";

interface MetricsProps {
  selectedBooks: BookSearchResult[];
}

function Metrics({ selectedBooks }: MetricsProps) {
  const [comparison, setComparison] = useState<ComparisonResult | null>(null);

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    // Don't call the API until exactly two books are selected
    if (selectedBooks.length !== 2) {
      setComparison(null);
      setLoading(false);
      setError(null);
      return;
    }

    let cancelled = false;

    const loadComparison = async () => {
      setLoading(true);
      setError(null);

      try {
        const result = await compareBooks(
          selectedBooks[0].id,
          selectedBooks[1].id,
        );

        if (!cancelled) {
          setComparison(result);
        }
      } catch (error) {
        console.error("Failed to compare books:", error);

        if (!cancelled) {
          setError("Failed to load comparison.");
          setComparison(null);
        }
      } finally {
        if (!cancelled) {
          setLoading(false);
        }
      }
    };

    loadComparison();

    return () => {
      cancelled = true;
    };
  }, [selectedBooks]);

  // Don't display anything until two books are selected
  if (selectedBooks.length !== 2) {
    return null;
  }

  if (loading) {
    return (
      <section className="metrics">
        <h2>Divergence Metrics</h2>
        <p>Calculating comparison...</p>
      </section>
    );
  }

  if (error) {
    return (
      <section className="metrics">
        <h2>Divergence Metrics</h2>
        <p>{error}</p>
      </section>
    );
  }

  if (!comparison) {
    return null;
  }

  return (
    <section className="metrics">
      <h2>Divergence Metrics</h2>

      <div className="metrics-grid">
        <Metric
          number="i."
          title="Cosine Similarity"
          value={comparison.cosineSimilarity.toFixed(4)}
          description="Measures alignment of thematic vectors. Higher = more similar."
        />

        <Metric
          number="ii."
          title="KL Divergence (A‖B)"
          value={comparison.klDivergenceAB.toFixed(4)}
          description="Measures how much information is lost when approximating one distribution with the other."
        />

        <Metric
          number="iii."
          title="KL Divergence (B‖A)"
          value={comparison.klDivergenceBA.toFixed(4)}
          description="KL divergence is asymmetric — reversing the distributions can produce a different result."
        />

        <Metric
          number="iv."
          title="JS Distance"
          value={comparison.jsDivergence.toFixed(4)}
          description="Jensen-Shannon distance is symmetric and bounded [0,1]."
        />
      </div>
    </section>
  );
}

interface MetricProps {
  number: string;
  title: string;
  value: string;
  description: string;
}

function Metric({ number, title, value, description }: MetricProps) {
  return (
    <div className="metric">
      <div className="metric-number">{number}</div>

      <div className="metric-content">
        <h3>{title}</h3>
        <div className="metric-value">{value}</div>
        <p>{description}</p>
      </div>
    </div>
  );
}

export default Metrics;
