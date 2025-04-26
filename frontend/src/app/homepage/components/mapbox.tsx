import { MakeNodes } from '@/app/homepage/components/make-nodes';
import { MakePolygons } from '@/app/homepage/components/make-polygons';
import { useCenterContext } from '@/app/homepage/context/center-context';
import { CenterContextProps } from '@/app/homepage/interface/center-interface';
import { Button } from '@/components/ui/button';
import L from 'leaflet';
import 'leaflet/dist/leaflet.css';
import {
  MapContainer,
  Marker,
  Popup,
  TileLayer,
  useMapEvents,
} from 'react-leaflet';

// Fix for default marker icon issue
L.Icon.Default.prototype.options.iconUrl =
  'https://unpkg.com/leaflet@1.7.1/dist/images/marker-icon.png';
L.Icon.Default.prototype.options.iconRetinaUrl =
  'https://unpkg.com/leaflet@1.7.1/dist/images/marker-icon-2x.png';
L.Icon.Default.prototype.options.shadowUrl =
  'https://unpkg.com/leaflet@1.7.1/dist/images/marker-shadow.png';
L.Icon.Default.mergeOptions({
  iconRetinaUrl:
    'https://unpkg.com/leaflet@1.7.1/dist/images/marker-icon-2x.png',
  iconUrl: 'https://unpkg.com/leaflet@1.7.1/dist/images/marker-icon.png',
  shadowUrl: 'https://unpkg.com/leaflet@1.7.1/dist/images/marker-shadow.png',
});

function LocationMarker({ setCenter }: CenterContextProps) {
  useMapEvents({
    moveend(e) {
      const map = e.target;
      setCenter({ x: map.getCenter().lat, y: map.getCenter().lng });
    },
  });
  return null;
}

const MapBox = () => {
  const { center, setCenter, data, setData } = useCenterContext();

  return (
    <div>
      <div className="h-[600px] w-[600px]">
        <MapContainer
          center={[center.x, center.y]}
          zoom={13}
          scrollWheelZoom={true}
          style={{ height: '100%', width: '100%' }}>
          <TileLayer
            attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
          />
          <Marker position={[center.x, center.y]}>
            <Popup>
              You are here. <br />
            </Popup>
          </Marker>
          {data && <MakePolygons elements={data.elements} />}
          {data && <MakeNodes elements={data.elements} />}
          <LocationMarker
            center={center}
            setCenter={setCenter}
            data={data}
            setData={setData}
          />
        </MapContainer>
      </div>
      <Button
        className="m-1"
        onClick={() =>
          setCenter({ x: 54.39482637467512, y: 18.574318885803226 })
        }>
        Put possition on UG
      </Button>
    </div>
  );
};

export default MapBox;
