import { Dialog, Transition } from '@headlessui/react';
import { PlusIcon } from '@heroicons/react/24/outline';
import { useSession } from 'next-auth/react';
import { Fragment, useState } from 'react';
import { useCreateLabel } from '@/app/favourites/hooks/client/use-create-label';
import { Label } from '@/app/favourites/hooks/client/use-fetch-labels';

interface UpdateLabelsModalProps {
  isOpen: boolean;
  onClose: () => void;
  onSave: (selectedLabels: Label[]) => void;
  availableLabels: Label[];
  currentLabels: Label[];
}

export const UpdateLabelsModal = ({
  isOpen,
  onClose,
  onSave,
  availableLabels,
  currentLabels,
}: UpdateLabelsModalProps) => {
  const { data: session } = useSession();
  const [selectedLabels, setSelectedLabels] = useState<Label[]>(currentLabels);
  const [newLabel, setNewLabel] = useState<Label>({
    name: '',
    id: '',
    color: 'BLUE',
  });
  const [isAddingNew, setIsAddingNew] = useState(false);

  const { mutate: createLabel } = useCreateLabel(
    session?.tokens?.accessToken as string
  );

  const handleSave = () => {
    onSave(selectedLabels);
    onClose();
  };

  const handleAddNewLabel = () => {
    if (newLabel?.name) {
      createLabel(
        {
          name: newLabel.name,
          color: newLabel.color,
        },
        {
          onSuccess: (response) => {
            const createdLabel = response.data;
            setSelectedLabels((prev) => [...prev, createdLabel]);
            setNewLabel({
              name: '',
              id: '',
              color: 'BLUE',
            });
            setIsAddingNew(false);
          },
        }
      );
    }
  };

  return (
    <Transition
      show={isOpen}
      as={Fragment}>
      <Dialog
        onClose={onClose}
        className="relative z-50">
        <div
          className="fixed inset-0 bg-black/30"
          aria-hidden="true"
        />

        <div className="fixed inset-0 flex items-center justify-center p-4">
          <Dialog.Panel className="mx-auto max-w-sm rounded bg-white p-6">
            <Dialog.Title className="mb-4 text-lg font-medium">
              Update Labels
            </Dialog.Title>

            <div className="space-y-4">
              <div className="flex flex-wrap gap-2">
                {availableLabels.map((label) => (
                  <button
                    key={label.id}
                    onClick={() => {
                      setSelectedLabels((prev) =>
                        prev.includes(label)
                          ? prev.filter((l) => l !== label)
                          : [...prev, label]
                      );
                    }}
                    className={`rounded-full px-3 py-1 text-sm ${
                      selectedLabels.includes(label)
                        ? 'bg-blue-500 text-white'
                        : 'bg-gray-200'
                    }`}>
                    {label.name}
                  </button>
                ))}
              </div>

              {isAddingNew ? (
                <div className="flex gap-2">
                  <input
                    type="text"
                    value={newLabel?.name}
                    onChange={(e) =>
                      setNewLabel((prev) => ({ ...prev, name: e.target.value }))
                    }
                    className="flex-1 rounded border px-2 py-1"
                    placeholder="Enter new label"
                  />
                  <select
                    value={newLabel?.color}
                    onChange={(e) =>
                      setNewLabel((prev) => ({
                        ...prev,
                        color: e.target.value as Label['color'],
                      }))
                    }
                    className="rounded border px-2 py-1">
                    <option value="BLUE">Blue</option>
                    <option value="RED">Red</option>
                    <option value="PINK">Pink</option>
                    <option value="YELLOW">Yellow</option>
                    <option value="ORANGE">Orange</option>
                    <option value="GREEN">Green</option>
                    <option value="PURPLE">Purple</option>
                  </select>
                  <button
                    onClick={handleAddNewLabel}
                    className="rounded bg-blue-500 px-3 py-1 text-white">
                    Add
                  </button>
                </div>
              ) : (
                <button
                  onClick={() => setIsAddingNew(true)}
                  className="flex items-center gap-2 text-blue-500">
                  <PlusIcon className="h-4 w-4" />
                  Add new label
                </button>
              )}

              <div className="mt-6 flex justify-end gap-2">
                <button
                  onClick={onClose}
                  className="rounded px-4 py-2 text-gray-600 hover:bg-gray-100">
                  Cancel
                </button>
                <button
                  onClick={handleSave}
                  className="rounded bg-blue-500 px-4 py-2 text-white hover:bg-blue-600">
                  Save
                </button>
              </div>
            </div>
          </Dialog.Panel>
        </div>
      </Dialog>
    </Transition>
  );
};
