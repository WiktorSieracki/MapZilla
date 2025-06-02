import { Label } from '@/app/favourites/hooks/client/use-fetch-labels';
import { Badge } from '@/components/ui/badge';

interface FilterLabelsProps {
  labels: Label[];
  selectedLabels: Label[];
  onLabelToggle: (label: Label) => void;
}

export const FilterLabels = ({
  labels,
  selectedLabels,
  onLabelToggle,
}: FilterLabelsProps) => {
  return (
    <div className="mb-4 flex flex-wrap gap-2">
      {labels.map((label) => (
        <Badge
          key={label.id}
          variant={selectedLabels.includes(label) ? 'default' : 'outline'}
          className="cursor-pointer"
          onClick={() => onLabelToggle(label)}>
          {label.name}
        </Badge>
      ))}
    </div>
  );
};
